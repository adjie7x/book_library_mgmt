Unit Test Steps
1. Arrange / Set Up
2. Action
3. Assertion


Create a simple book library management :

1. Book (id, isbn, title, author, status (shelved/not_shelved))
2. Shelf (shelf_id, max_capacity, current_capacity, books)
3. Create Service (Include Repository) for :
              - Add book into shelf (update book status, shelf current_capacity)
              - Remove book from shelf (update book status, shelf current_capacity)
4. Create controller (REST) for :
              A. Book Controller 
                             - display all books by status
                             - display books by title and status
              B. Library Controller 
                             - display shelf by shelf_id
                             - add a book into a shelf
                             - remove a book from a shelf
5. Create Unit Test for Service and Controller
6. Create simple Integration Test
-------------------------------------------------------------
Hint : 
1.           Use SpringBoot Web and JPA
2.           Use JpaRepository for Repository
3.           Use Mockito and WebMvcTest
-------------------------------------------------------------

Code for mentoring today could be found at https://github.com/esarijal/javaunittesting and attached in this email too. 

After finishing this, please push into your github account and send me the link
