db = db.getSiblingDB('purely_product_service');

db.products.drop();

var products = cat('/docker-entrypoint-initdb.d/purely_product_service.products.json');  // قراءة ملف JSON
db.products.insertMany(JSON.parse(products));  // إدخال البيانات

print("✅ Products data inserted successfully!");
