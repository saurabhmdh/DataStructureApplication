/*
 * C program to input N numbers and store them in an array.
 * Do a linear search for a given key and report success
 * or failure.
 */
#include <stdio.h>
 
void main()
{
    int array[10];
    int i, num, keynum, found = 0;
 
    printf("Enter the value of num \n");
    scanf("%d", &num);
    printf("Enter the elements one by one \n");
    for (i = 0; i < num; i++)
    {
        scanf("%d", &array[i]);
    }
    printf("Input array is \n");
    for (i = 0; i < num; i++)
    {
        printf("%dn", array[i]);
    }
    printf("Enter the element to be searched \n");
    scanf("%d", &keynum);
    /*  Linear search begins */
    for (i = 0; i < num ; i++)
    {
        if (keynum == array[i] )
        {
            found = 1;
            break;
        }
    }
    if (found == 1)
        printf("Element is present in the array\n");
    else
        printf("Element is not present in the array\n");
}

Output:
    Enter the value of num
    5
    Enter the elements one by one
    456
    78
    90
    40
    100
    Input array is
    456
    78
    90
    40
    100
    Enter the element to be searched
    70
    Element is not present in the array
     
    
    Enter the value of num
    7
    Enter the elements one by one
    45
    56
    89
    56
    90
    23
    10
    Input array is
    45
    56
    89
    56
    90
    23
    10
    Enter the element to be searched
    45
    Element is present in the array