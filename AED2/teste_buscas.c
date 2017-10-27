#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define MAX 15
#define NUMVET 1000

int comp_seq = 0;
int comp_binary = 0;
int comp_binary2 = 0;

void randomize(int *vet){
    int i,a;

    //printf("Vet: ");
    for(i = 0; i < MAX; i++){
        a = 1 + rand()%15;
        vet[i] = a;
        //printf("%d ", a);
    }
}

int sequential_search(int *vet, int n){
    int i;

    for(i = 0; i < MAX; i++){
        comp_seq++;
        if(n == vet[i])
            return i;
    }
    return -1;
}

void test_sequential(int *vet){
    int i,*aux_pos,pos,count = 0;

    aux_pos = (int*) malloc(MAX*sizeof(int));
    for(i = 0; i < MAX; i++)
        aux_pos[i] = 0;

    while(count < NUMVET){
        randomize(vet);
        pos = sequential_search(vet, 10);
        if(pos != -1){
            aux_pos[pos]++;
            //printf("\n10 found at: %d\n\n", pos);
        }
        //else
            //printf("\n10 not found\n\n");
        count++;
    }

    printf("Sequential search:\n");
    printf("Number of comparisons: %d\n", comp_seq);
    printf("Relation: position - times that 10 was found\n");
    for(i = 0; i < MAX; i++)
        printf("%d - %d\n", i, aux_pos[i]);
}

int partition(int *vet, int start, int end){
    int pivot,index,temp,i;
    pivot = vet[end];
    index = start;

    for(i = start; i < end; i++){
       if(vet[i] <= pivot){
           temp = vet[i];
           vet[i] = vet[index];
           vet[index] = temp;
           index++;
       }
   }
   temp = vet[index];
   vet[index] = vet[end];
   vet[end] = temp;
   return index;
}

void quick_sort(int *vet, int start, int end){
    if(start >= end)
        return;

    int pivot = partition(vet, start, end);
    quick_sort(vet, start, pivot-1);
    quick_sort(vet, pivot+1, end);
}

int binary_search(int *vet, int n){
    int left,right,mid;

    left = 0;
    right = MAX-1;
    while(left <= right){
        comp_binary++;
        mid = (left + right)/2;
        if(vet[mid] == n)
            return mid;
        if(vet[mid] < n)
            left = mid+1;
        else
            right = mid-1;
    }
    return -1;
}

int rec_binary_search(int *vet, int left, int right, int n){
    if(left <= right){
        comp_binary2++;
        int mid = (left + right)/2;
        if(vet[mid] == n)
            return mid;
        if(vet[mid] < n)
            return rec_binary_search(vet, mid+1, right, n);
        else
            return rec_binary_search(vet, left, right-1, n);
    }
    return -1;
}

void test_iterative_binary_search(int *vet){
    int i,*aux_pos,pos,count = 0;

    aux_pos = (int*) malloc(MAX*sizeof(int));
    for(i = 0; i < MAX; i++)
        aux_pos[i] = 0;

    while(count < NUMVET){
        randomize(vet);
        quick_sort(vet, 0, MAX-1);
        pos = binary_search(vet, 10);
        if(pos != -1){
            aux_pos[pos]++;
            //printf("\n10 found at: %d\n\n", pos);
        }
        //else
            //printf("\n10 not found\n\n");
        count++;
    }

    printf("\nIterative binary search:\n");
    printf("Number of comparisons: %d\n", comp_binary);
    printf("Relation: position - times that 10 was found\n");
    for(i = 0; i < MAX; i++)
        printf("%d - %d\n", i, aux_pos[i]);
}

void test_recursive_binary_search(int *vet){
    int i,*aux_pos,pos,count = 0;

    aux_pos = (int*) malloc(MAX*sizeof(int));
    for(i = 0; i < MAX; i++)
        aux_pos[i] = 0;

    while(count < NUMVET){
        randomize(vet);
        quick_sort(vet, 0, MAX-1);
        pos = rec_binary_search(vet, 0, MAX-1, 10);
        if(pos != -1){
            aux_pos[pos]++;
            //printf("\n10 found at: %d\n\n", pos);
        }
        //else
            //printf("\n10 not found\n\n");
        count++;
    }

    printf("\nRecursive binary search:\n");
    printf("Number of comparisons: %d\n", comp_binary2);
    printf("Relation: position - times that 10 was found\n");
    for(i = 0; i < MAX; i++)
        printf("%d - %d\n", i, aux_pos[i]);
}

int main(){
    int *vet,pos,i;

    vet = (int*) malloc(MAX*sizeof(int));

    srand(time(NULL));

    test_sequential(vet);

    test_iterative_binary_search(vet);

    test_recursive_binary_search(vet);

    free(vet);

    return 0;
}
