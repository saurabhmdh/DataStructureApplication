// A complete working C program to demonstrate deletion in singly
// linked list
#include <stdio.h>
#include <stdlib.h>

// A linked list node
struct node
{
	int data;
	struct node *next;
};

/* Given a reference (pointer to pointer) to the head of a list
and an int, inserts a new node on the front of the list. */
void push(struct node** head_ref, int new_data)
{
	struct node* new_node = (struct node*) malloc(sizeof(struct node));
	new_node->data = new_data;
	new_node->next = (*head_ref);
	(*head_ref) = new_node;
}

/* Given a reference (pointer to pointer) to the head of a list
and a key, deletes the first occurrence of key in linked list */
void deleteNode(struct node **head_ref, int key)
{
	// Store head node
	struct node* temp = *head_ref, *prev;

	// If head node itself holds the key to be deleted
	if (temp != NULL && temp->data == key)
	{
		*head_ref = temp->next; // Changed head
		free(temp);			 // free old head
		return;
	}

	// Search for the key to be deleted, keep track of the
	// previous node as we need to change 'prev->next'
	while (temp != NULL && temp->data != key)
	{
		prev = temp;
		temp = temp->next;
	}

	// If key was not present in linked list
	if (temp == NULL) return;

	// Unlink the node from linked list
	prev->next = temp->next;

	free(temp); // Free memory
}

// This function prints contents of linked list starting from 
// the given node
void printList(struct node *node)
{
	while (node != NULL)
	{
		printf(" %d ", node->data);
		node = node->next;
	}
}

/* Drier program to test above functions*/
int main()
{
	/* Start with the empty list */
	struct node* head = NULL;

	push(&head, 7);
	push(&head, 1);
	push(&head, 3);
	push(&head, 2);

	puts("Created Linked List: ");
	printList(head);
	deleteNode(&head, 1);
	puts("\nLinked List after Deletion of 1: ");
	printList(head);
	return 0;
}


Output:
Created Linked List:
 2  3  1  7
Linked List after Deletion of 1:
 2  3  7