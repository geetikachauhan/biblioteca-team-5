ALTER TABLE biblioteca.customer ADD COLUMN email VARCHAR;
ALTER TABLE biblioteca.customer ADD COLUMN phone VARCHAR;

UPDATE biblioteca.customer set email ='Dave@gmail.com' , phone = '1234567890' where library_number = '124-4567';
UPDATE biblioteca.customer set email ='Ava@gmail.com' , phone = '9876543210' where library_number ='456-5566';