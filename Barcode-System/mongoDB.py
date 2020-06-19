import pymongo
# import dnspython
from pprint import pprint
from jyinterface import pythonInterp


# connect to MongoDB, change the << MONGODB URL >> to reflect your own connection string
def connect2Server():
    client = pymongo.MongoClient('mongodb://localhost:27017')
    # "mongodb+srv://kirisuna:tlyh23792379@clustermaxima-jes42.mongodb.net/test?retryWrites=true&w=majority")
    db = client.barcodeSystem
    col = db["stockTracker"]
    return db, col


def serverStatus(db):
    # Issue the serverStatus command and print the results
    serverStatusResult = db.command("serverStatus")
    pprint(serverStatusResult)
    pprint(db.list_collection_names())


# Adding new items into the system (sets quantity as 0)
def add2Col(col, item):
    if not col.find({"ID": item[0]}).limit(1):
        mydict = {"ID": item[0], "productName": item[1], "quantity": 0, "supplier": item[2], "sellPrice": item[3],
                  "costPrice": item[4]}
        col.insert_one(mydict)
        return "Item added!"
    else:
        return "Item is already in the system!!"


# Remove item from the system forever (i.e. when item is not being sold anymore)
def remFromCol(col, query):
    myQuery = {"ID": query}
    if col.find(myQuery).limit(1):
        col.delete_one(myQuery)
        return "Item deleted!"
    else:
        return "Item is not in the system!!"


# Return sell price/cost price/quantity/ product name/ supplier
def returnField(col, itemID, field):
    myQuery = {"ID": itemID}
    if col.find(myQuery).limit(1):
        for xx in col.find(myQuery, {field: 1}):
            for key, val in xx.items():
                return val
    else:
        return -1


# Update quantity of item. True for sales, False for update stock
# Might throw error instead when not in system (?)
def updateQuantitySale(col, productName, polarity, amount):
    myQuery = {"productName": productName}
    if col.find(myQuery).limit(1):
        for xx in col.find(myQuery, {"quantity": 1}):
            for key, val in xx.items():
                if polarity:
                    newValue = {"$set": {"Quantity": val - amount}}
                else:
                    newValue = {"$set": {"Quantity": val + amount}}
        col.update_one(myQuery, newValue)
        return newValue
    else:
        return -1


# Update sell price/ cost price/ product name/ supplier
# Might throw error instead when not in system (?)
def updateTable(col, itemID, field, newEntry):
    myQuery = {"ID": itemID}
    if col.find(myQuery).limit(1):
        newValue = {"$set": {field: newPrice}}
        col.update_one(myQuery,newValue)
        return newPrice
    else:
        return -1


def addTemp(db, col):
    mydict = {"name": "king", "address" : "drakedrake", "number": 0}
    col.insert_one(mydict)


def findTemp(db, col):
    hello = "name"
    for x in col.find({hello: "king"}, {"_id": 0, "name": 0, "address": 0}):
        hell = x
        for key, val in hell.items():
            pprint (val + 1)
# Update in Python only updates the table if query exist, otherwise nothing is done. (Does not add new entry)

db, col = connect2Server()
