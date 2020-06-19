package mainSystem;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("unused")
public class mongoDB {
	static MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	static MongoDatabase database = mongoClient.getDatabase("barcodeSystem");
	static MongoCollection<Document> admin = database.getCollection("Admin");
	static MongoCollection<Document> collection = database.getCollection("stockTracker");
	static MongoCollection<Document> cart = database.getCollection("cart");
	static MongoCollection<Document> sale = database.getCollection("sales");
	static MongoCollection<Document> special = database.getCollection("specials");
	public static void main(String []args){
		// Suppress warning only here because explicit closing of connection is not required
		// Connect to mongo server and retrieve database/collection
		//@SuppressWarnings("resource")		
		//MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		//MongoDatabase database = mongoClient.getDatabase("barcodeSystem");
		//MongoCollection<Document> collection = database.getCollection("stockTracker");
		//MongoCollection<Document> cart = database.getCollection("cart");
		//MongoCollection<Document> sale = database.getCollection("sales");
		//MongoCollection<Document> special = database.getCollection("specials");
		//MongoCollection<Document> admin = database.getCollection("Admin");
		//int s = mongoDB.ifExist(admin,"adminID", "kirisuna");
		//System.out.println(s);
	}
	
	// DO I NEED THIS?? (localdb probably not (?)
	// Check server status
	// static void serverStat(MongoDatabase db, MongoCollection<Document> col) {
	// }

//--------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------
	
	// Add item into the stockTracker collection
	// Return 1 if successful, otherwise -1
	// Quantity set to 0 by default if blank
	static int add2Col(MongoCollection<Document> col, String[] item ) {
		if (col.count(eq("barcodeID", item[0])) > 0) {
			return -1;
		} else {
			int quantity = 0;
			Double cost = 0.0;
			Double sell = 0.0;
			int sales = 0;
			
			if (item[2] != null) {
				quantity = Integer.parseInt(item[2]);
			}
			if (item[4] != null) {
				cost = Double.parseDouble(item[4]);
			}
			if (item[5] != null) {
				sell = Double.parseDouble(item[5]);
			}
			if (item[6] != null) {
				sales = Integer.parseInt(item[6]);
			}
			
			Document doc = new Document("barcodeID", item[0])
					.append("productName", item[1])
					.append("quantity", quantity)
					.append("supplier", item[3])
					.append("costPrice", cost)
					.append("sellPrice", sell)
					.append("totalSales", sales);
			col.insertOne(doc);
			return 1;
		}
	}
	
	// Update the quantity of an item in the stockTracker collection
	// Uses barcodeID, therefore scan item to update
	// + if polarity == true, - if polarity == false
	// No need to check for wrong types here, will be done when passing info later
	static int updateQuantitySale(MongoCollection<Document> col, String query, boolean polarity, int amount, boolean refund) {
		if (col.count(eq("barcodeID", query)) > 0) {
			String[] extractQ = returnField(col,  "barcodeID", query, "quantity");
			String[] extractS = returnField(col, "barcodeID", query, "totalSales");
			int currentQ = 0;
			int currentS = 0;
			if (polarity == true) {
				currentQ = Integer.parseInt(extractQ[0]) - amount;
				currentS = Integer.parseInt(extractS[0]) + amount;
				System.out.print(currentQ);
				System.out.print(currentS);
			} else {
				currentQ = Integer.parseInt(extractQ[0]) + amount;
				if (refund) {
					currentS = Integer.parseInt(extractS[0]) - amount;
				}
			}
			Bson newValue = new Document ("quantity", currentQ)
					.append("totalSales", currentS);
			Bson updater = new Document("$set", newValue);
			col.updateOne(eq("barcodeID",query), updater);
			return currentQ;
		} else {
			return -1;
		}
	}
	
	// Update/replace fields of items in the stockTracker collection
	// No need to check for wrong types here, will be done when passing info later
	static String[] updateField(MongoCollection<Document> col, String query, String desired, String newEntry) {
		String[] temp = {"null", "-1"};
		if (col.count(eq("barcodeID", query)) > 0) {
			Bson newValue = new Document (desired, newEntry);
			if (desired == "sellPrice" || desired == "costPrice") {
				Double intEntry = Double.parseDouble(newEntry);
				newValue = new Document (desired, intEntry);
			} else if (desired == "quantity") {
				int intEntry = Integer.parseInt(newEntry);
				newValue = new Document (desired, intEntry);
			}
			Bson updater = new Document("$set",newValue);
			col.updateOne(eq("barcodeID",query), updater);
			temp[0] = newEntry;
			temp[1] = "1";
			return temp;
		} else {
			return temp;
		}
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------
	
	// Insert object into cart
	static int insertCart(MongoCollection<Document> col, MongoCollection<Document> cart,  String query) {
		if (col.count(eq("barcodeID", query)) > 0) {
			String[] extractName = returnField(col, "barcodeID", query, "productName");
			String[] extractPrice = returnField(col, "barcodeID", query, "sellPrice");
			if (cart.count(eq("productName", extractName[0])) > 0) {
				String[] extractQ = returnField(cart, "productName", extractName[0], "quantity");
				int currentQ = Integer.parseInt(extractQ[0]) + 1;
				Bson newValue = new Document ("quantity", currentQ).append("price", currentQ * Float.parseFloat(extractPrice[0]));
				Bson updater = new Document ("$set",newValue);
				cart.updateMany(eq("productName",extractName[0]), updater);
				return 1;
			} else {
				Document doc = new Document("productName", extractName[0])
						.append("quantity", 1)
						.append("price", Double.parseDouble(extractPrice[0]))
						.append("barcodeID", query);
				cart.insertOne(doc);
				return 1;
			}
		} else {
			return -1;
		}
	}
	
	// Remove object from cart
	static int removeCart(MongoCollection<Document> col, MongoCollection<Document> cart,  String query) {
		if (col.count(eq("barcodeID", query)) > 0) {
			String[] extractName = returnField(col, "barcodeID", query, "productName");
			String[] extractPrice = returnField(col, "barcodeID", query, "sellPrice");
			if (cart.count(eq("productName", extractName[0])) > 0) {
				String[] extractQ = returnField(cart, "productName", extractName[0], "quantity");
				int currentQ = Integer.parseInt(extractQ[0]) - 1;
				if (currentQ <= 0) {
					cart.deleteOne(eq("productName",extractName[0]));
					return 1;
				} else {
					Bson newValue = new Document ("quantity", currentQ).append("price", currentQ * Float.parseFloat(extractPrice[0]));
					Bson updater = new Document ("$set",newValue);
					cart.updateMany(eq("productName",extractName[0]), updater);
					return 1;
				}
			}
			return -1;
		}
		return -1;
	}
	
	// Insert current cart (dropped) into the sales collection
	static void insertSales(MongoCollection<Document> dropped, MongoCollection<Document> sales, String total) {
		ArrayList<Object> arrlist = new ArrayList<Object>();
		FindIterable<Document> iterable = dropped.find();
		MongoCursor<Document> cursor = iterable.iterator();
		int count = 0;
		try {
			while(cursor.hasNext()){
				Document temp =  cursor.next();
				arrlist.add(temp.get("productName"));
				count += temp.getInteger("quantity");
			}
		} finally {
			cursor.close();
		}
		
		SimpleDateFormat Perth = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat perthTime = new SimpleDateFormat("h:mm:ss", Locale.KOREA);
		Document doc = new Document ("itemsSold",arrlist)
				.append("total", total)
				.append("quantity", count)
				.append("date", Perth.format(new Date()))
				.append("time", perthTime.format(new Date()));
		sales.insertOne(doc);
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------
	
	// Check if query exist in the database
	static int ifExist(MongoCollection<Document> col, String key, String query) {
		if (col.count(eq(key,query))>0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	// Return desired field for query
	// Return 1 if successful, otherwise -1
	static String[] returnField(MongoCollection<Document> col, String key, String query, String desired) {
		String[] temp = {"null", "-1"};
		if (col.count(eq(key, query)) > 0) {
			Document myDoc = col.find(eq(key,query)).first();
			Object obs = desired;
			Object returned = myDoc.get(obs);
			if (returned instanceof Integer) {
				temp[0] = returned.toString();
			} else if (returned instanceof Double) {
				temp[0] = returned.toString();
			} else {
				temp[0] = (String) returned;
			}
			temp[1] = "1";
			return temp;
		} else {
			return temp;
		}
	}
	
	// Remove all object from the collection
	static void clearCollection(MongoCollection<Document> col) {
		BasicDBObject document = new BasicDBObject();
		col.deleteMany(document);
	}
	
	// Remove item from the collection
	// Return 1 if successful, otherwise -1
	static int remFromCol(MongoCollection<Document> col, String key, String query) {
		if (col.count(eq(key, query)) > 0) {
			col.deleteOne(eq(key, query));
			return 1;
		} else {
			return -1;
		}
		
	}
	
	//For calculator
	static boolean calcCheck(String text) {
	    if (text.contentEquals("0")) {
	    	return false;
	    } else {
	    	return true;
	    }
	  }
	
	
}

// Set box value to null if left empty
// When printing float, do %2f
// Remember to check for type casting