/*
 * C Program to Perform Shell Sort without using Recursion
 */
#include  <stdio.h>
#define size 7
 
/* Function Prototype */
int shell_sort(int []);
 
void main()
{
    int arr[size], i;
    printf("Enter the elements to be sorted:");
    for (i = 0;i < size;i++)
    {
        scanf("%d", &arr[i]);
    }
    shell_sort(arr);
    printf("The array after sorting is:");
    for (i = 0;i < size;i++)
    {
        printf("\n%d", arr[i]);
    }
}
 
/* Code to sort array using shell sort */
int shell_sort(int array[])
{
    int i = 0, j = 0, k = 0, mid = 0;
    for (k = size / 2;k > 0;k /= 2)
    {
        for (j = k;j < size;j++)
        {
            for (i = j - k;i >= 0;i -= k)
            {
                if (array[i + k] >= array[i])
                {
                    break;
                }
                else
                {
                    mid = array[i];
                    array[i] = array[i + k];
                    array[i + k] = mid;
                }
            }
        }
    }
    return 0;
}

Output:
    //Average case:

    Enter the elements to be sorted:57
    67
    48
    93
    42
    84
    95
    The array after sorting is:
    42
    48
    57
    67
    84
    93
    95
     
    //Best case:

    Enter the elements of array:22
    33
    74
    85
    86
    87
    98
    The array after sorting is:22
    33
    74
    85
    86
    87
    98
     
    //Worst case:

    Enter the elements of array:94
    92
    91
    89
    85
    80
    43
    The array after sorting is:43
    80
    85
    89
    91
    92
    94