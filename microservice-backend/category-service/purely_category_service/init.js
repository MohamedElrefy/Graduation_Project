db = db.getSiblingDB('purely_category_service');

db.categories.drop();

var categories = cat('/docker-entrypoint-initdb.d/purely_category_service.categories.json');  // قراءة ملف JSON
db.categories.insertMany(JSON.parse(categories));  // إدخال البيانات

print("✅ Categories data inserted successfully!");
