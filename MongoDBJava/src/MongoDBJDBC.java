import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBJDBC {
	public static void main(String args[]) {
		try {
			@SuppressWarnings("resource")
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase db = mongoClient.getDatabase("mydb");
			MongoCollection<Document> testData = db.getCollection("testData");
			Document document = new Document("name", "zx").append("value", 1);
			testData.dropCollection();
			testData.insertOne(document);
			FindIterable<Document> data = testData.find();
			MongoCursor<Document> iterator = data.iterator();
			try {
				while (iterator.hasNext()) {
					document = iterator.next();
					System.out.println(document);
				}
				System.out.println("Connect to database successfully");

			} finally {
				iterator.close();
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
		}
	}
}