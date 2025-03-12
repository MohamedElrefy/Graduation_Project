// 🔹 الاتصال بقاعدة البيانات مع المصادقة
db = connect("mongodb://root:example@localhost:27017/purely_category_service?authSource=admin");

// 🔹 حذف أي بيانات قديمة في المجموعة
db.categories.drop();

// 🔹 قراءة ملف JSON من المسار الصحيح
var categories = cat('/docker-entrypoint-initdb.d/purely_category_service.categories.json');

// 🔹 تحويل البيانات إلى JSON
var parsedCategories = JSON.parse(categories);

// 🔹 تصحيح _id ليكون ObjectId بدلاً من $oid
parsedCategories = parsedCategories.map(item => {
    if (item._id && item._id.$oid) {
        item._id = ObjectId(item._id.$oid);
    }
    return item;
});

// 🔹 إدخال البيانات في مجموعة `products`
db.categories.insertMany(parsedCategories);

print("✅ categories data inserted successfully!");
