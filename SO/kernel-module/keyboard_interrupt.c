#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/interrupt.h>
#include <asm/io.h>
#include <linux/reboot.h>
#include <linux/workqueue.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Joao Daniel");
MODULE_DESCRIPTION("Keyboard Interrupt");

static void wq_handler(struct work_struct *w); // Declarando a funcao chamada pela delayed work

static struct workqueue_struct *wq; // Criando a estrutura work queue
DECLARE_DELAYED_WORK(my_work, wq_handler); // Criando a estrutura delayed work

static unsigned int ctrl = 0;
static unsigned int alt = 0;
static unsigned int backspace = 0;
static unsigned int enter = 0;

static unsigned int time_flag = 0;
static unsigned int my_map[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}; // Estrutura para mapear os numeros do teclado
static unsigned int sec_to_shutdown = 0;

static void wq_handler(struct work_struct *w){ // Funcao a ser chamada pela delayed work queue
  kernel_power_off();
}

// Funcao ao ser executada para cada tecla pressionada ou solta
static irqreturn_t my_interrupt(int irq, void *dev_id){
  static unsigned char scancode;

  // Identificando o codigo hexadecimal da tecla apertada ou solta
  scancode = inb(0x60);
 
  if(!time_flag){
    if(scancode == 0x1d)
      ctrl = 1;
    else if(scancode == 0x38)
      alt = 1;
    else if(scancode == 0x0e)
      backspace = 1;
    else if(scancode == 0x1c)
      enter = 1;
    else{
      ctrl = 0;
      alt = 0;
      backspace = 0;
      enter = 0;
    }

    if(ctrl && alt && backspace){ // Desligar apos um tempo
      time_flag = 1;
      enter = 0;
    }
    else if(ctrl && alt && enter){ // Desligar direto
       kernel_power_off();
    }
  }

  else if(time_flag){
    if(scancode >= 0x02 && scancode <= 0x0b)
      sec_to_shutdown = (sec_to_shutdown * 10) + my_map[scancode-2]; // Recebendo os numeros digitados

    if(wq && scancode == 0x1c){ // Iniciando o tempo para desligamento apos apertar Enter
      unsigned int t = msecs_to_jiffies(sec_to_shutdown*1000);
      queue_delayed_work(wq, &my_work, t); // Colocando a estrutura my_work para ser executada apos o tempo t
      //time_flag = 0; // Variavel para debug
    }
  }
 
  return IRQ_HANDLED;
}

static int __init test_init(void){
  printk(KERN_INFO "Modulo inicializado\n");

  // Criando a work queue
  wq = create_singlethread_workqueue("my_wq");

  // Criando a interrupcao de teclado
  return request_irq(1, my_interrupt, IRQF_SHARED, "keyboard", (void *)my_interrupt);
}

static void __exit test_exit(void){
  free_irq(1, (void *)my_interrupt); //Liberando a interrupcao

  destroy_workqueue(wq); //Destroindo o work queue

  printk(KERN_INFO "Modulo fechado\n");
}

module_init(test_init);
module_exit(test_exit);
