/*
 * C program to accept N numbers sorted in ascending order
 * and to search for a given number using binary search.
 * Report success or failure.
 */
#include <stdio.h>
 
void main()
{
    int array[10];
    int i, j, num, temp, keynum;
    int low, mid, high;
 
    printf("Enter the value of num \n");
    scanf("%d", &num);
    printf("Enter the elements one by one \n");
    for (i = 0; i < num; i++)
    {
        scanf("%d", &array[i]);
    }
    printf("Input array elements \n");
    for (i = 0; i < num; i++)
    {
        printf("%d\n", array[i]);
    }
    /*  Bubble sorting begins */
    for (i = 0; i < num; i++)
    {
        for (j = 0; j < (num - i - 1); j++)
        {
            if (array[j] > array[j + 1])
            {
                temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }
    printf("Sorted array is...\n");
    for (i = 0; i < num; i++)
    {
        printf("%d\n", array[i]);
    }
    printf("Enter the element to be searched \n");
    scanf("%d", &keynum);
    /*  Binary searching begins */
    low = 1;
    high = num;
    do
    {
        mid = (low + high) / 2;
        if (keynum < array[mid])
            high = mid - 1;
        else if (keynum > array[mid])
            low = mid + 1;
    } while (keynum != array[mid] && low <= high);
    if (keynum == array[mid])
    {
        printf("SEARCH SUCCESSFUL \n");
    }
    else
    {
        printf("SEARCH FAILED \n");
    }
}

Output:
    Enter the value of num
    5
    Enter the elements one by one
    23
    90
    56
    15
    58
    Input array elements
    23
    90
    56
    15
    58
    Sorted array is...
    15
    23
    56
    58
    90
    Enter the element to be searched
    58
    SEARCH SUCCESSFUL
     

    Enter the value of num
    4
    Enter the elements one by one
    1
    98
    65
    45
    Input array elements
    1
    98
    65
    45
    Sorted array is...
    1
    45
    65
    98
    Enter the element to be searched
    6
    SEARCH FAILED