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
 

## Items 
Endpoints for fetching all Items

#### View all products
Returns all the items that are currently available in the inventory.
If the query param `showInStockOnly` is specified and is true, only in-stock items are returned.

**Endpoint:** `GET: /`  
**Query Params:**
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