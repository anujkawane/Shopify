# Shopify Backend Developer Intern Challenge - Summer 2022

# Implemented feature

 > When deleting, allow deletion of item comments and undeletion.

# Examples of Requests

## 1. Create item
	curl --location --request POST 'http://localhost:8080/api/v1/items' \
    --header 'Content-Type: application/json' \
    --data-raw '{
      "name": "Laptop",
      "category": "electronics",
      "price": 50,
      "quantity": 5,
      "invoiceNumber": 1233456
    }'


## 2. Read item by its id
	curl --location --request GET 'http://localhost:8080/api/v1/items'

## 3. Read items if inStock

    curl --location --request
        GET 'http://localhost:8080/api/v1/items?showInStockOnly=true'

## 4. Update item
 Dynamic updation of item has been implemented, user can update item by changing zero or more field at the same time.

	curl --location --request PATCH 'http://localhost:8080/api/v1/items/1' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        
            "name": "Mobile",
            "quantity": 0
            
            // add available field and values
        }'

## 5. Delete item
	curl --location --request DELETE 'http://localhost:8080/api/v1/items/3' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "comments" : "Reason to delete ID"
        }'

## 6. Undo the deleted item
	curl --location --request 
        PATCH 'http://localhost:8080/api/v1/items/undoDelete/1'


## API Description
### Background
In this Inventory tracking application, user can have access to all items available under different categories.

**Endpoint:** `/api/v1/items`

## Create Item 


Create item by providing the 


**Endpoint:** `POST: /`  
**Sample Request Body:**
```json 
{
    "name": "HeadPhones",
    "category": "electronics",
    "price": 200.0,
    "quantity": 10,
    "invoiceNumber": 98765
}
```
**Sample Response:**
```json
{
  "id": 1,
  "name": "HeadPhones",
  "category": "electronics",
  "price": 200.0,
  "quantity": 10,
  "invoiceNumber": 98765,
  "createdAt": "2022-01-19T20:41:21.359773-08:00",
  "updatedAt": "2022-01-19T20:41:21.360231-08:00",
  "active": true
}
```

## View Items
Returns all the items that are currently available in the inventory.
If the query param `showInStockOnly` is specified and is true, only in-stock items are returned.

**Endpoint:** `GET: /`  
**Query Params:*

**Note:** `showInStockOnly` If this parameter is specified, it will return all of the items that are currently in stock. 
- `showInStockOnly`
    - type: `boolean`
    - required: `false`
    - default: `false`

**Sample Response:**
```json
[
  {
    "id": 1,
    "name": "HeadPhones",
    "category": "electronics",
    "price": 200.0,
    "quantity": 10,
    "invoiceNumber": 98765,
    "createdAt": "2022-01-19T20:41:21.359773-08:00",
    "updatedAt": "2022-01-19T20:41:21.360231-08:00",
    "active": true
  }
]
```

## Update item
Returns the updated cart details where the product you specified to be added is present and the total cost reflects those changes.
An exception is thrown if the cart is not present or if the product is not present or the product is already present in the cart.

**Endpoint:** `PATCH: /{id}`

**Sample Request Body:**
```json
{
  "name": "FaceWash",
  "category": "cosmetics",
  "price": 25.0,
  "quantity": 50
}
```


**Sample Response:**
```json
[
  {
    "id": 2,
    "name": "FaceWash",
    "category": "cosmetics",
    "price": 25.0,
    "quantity": 50,
    "invoiceNumber": 98765,
    "createdAt": "2022-01-19T20:41:21.359773-08:00",
    "updatedAt": "2022-01-19T20:41:25.360231-08:00",
    "active": true
  }
]
```

## Background
I used Java and the Spring Boot framework for this project because I believed it would speed up development. For simplicity, I'm using an in-memory database (H2 Database), but this can easily be modified to use a database such as Postgres by updating the dependencies in 'build.gradle' and defining a few application properties.