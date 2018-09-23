/*
 * C Program to Perform Binary Search using Recursion
 */
#include <stdio.h>
 
void binary_search(int [], int, int, int);
void bubble_sort(int [], int);
 
int main()
{
    int key, size, i;
    int list[25];
 
    printf("Enter size of a list: ");
    scanf("%d", &size);
    printf("Generating random numbers\n");
    for(i = 0; i < size; i++)
    {
        list[i] = rand() % 100;
        printf("%d  ", list[i]);
    }
    bubble_sort(list, size);
    printf("\n\n");
    printf("Enter key to search\n");
    scanf("%d", &key);
    binary_search(list, 0, size, key);
 
}
 
void bubble_sort(int list[], int size)
{
    int temp, i, j;
    for (i = 0; i < size; i++)
    {
        for (j = i; j < size; j++)
        {
            if (list[i] > list[j])
            {
                temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }
    }
}
 
void binary_search(int list[], int lo, int hi, int key)
{
    int mid;
 
    if (lo > hi)
    {
        printf("Key not found\n");
        return;
    }
    mid = (lo + hi) / 2;
    if (list[mid] == key)
    {
        printf("Key found\n");
    }
    else if (list[mid] > key)
    {
        binary_search(list, lo, mid - 1, key);
    }
    else if (list[mid] < key)
    {
        binary_search(list, mid + 1, hi, key);
    }
}}

Output:
    Enter size of a list: 10
    Generating random numbers
    83  86  77  15  93  35  86  92  49  21  
     
    Enter key to search
    21
    Key found