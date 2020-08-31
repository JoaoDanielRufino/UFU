Prática - 06:

Requisitos:
    - Python 3.6+
    - pip ou pip3
Como executar:
    -1º Criar um ambiente: python3 -m venv venv 
        - a) Para ativar o ambiente em linux (Ubuntu): source ./venv/bin/activate
        - b) Para ativar o ambiente em Windows: venv\Scripts\activate
    -2º Instalar as dependências: pip3 install -r requirements
    -3º Para executar o programa:
        - a) Para usar a distância euclidiana: python3 main.py ./dataset/iris.data
            - a.1) Para setar o número de cluster use o comando --k, por padrão irá ser 3
            - a.2) Para setar o número de iterações use o comando --i, por padrão é 100
            - a.3) Para seta o número de tolerância use o comando --tol, por padrão é 0.001
            Exemplos de query:
            python3 main.py ./dataset/iris.data --it=100
            python3 main.py ./dataset/iris.data --k=5 --it=100 
            python3 main.py ./dataset/iris.data --k=2 --it=20 --tol=0.0001

            OBS: Não necessáriamente precisa dos 3 parâmetrOBS.python3.
                 Para saber mais como executar use o comando python3 main.py -h 


        OBS: Onde está ./dataset/iris.data pode ser usado qualquer outro path para seu dataset
