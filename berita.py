import firebase_admin
from firebase_admin import credentials, firestore

# Inisialisasi Firebase
cred = credentials.Certificate("C:\\projek AKU\\dummyDataFirebase\\motofinder-2f3fb-firebase-adminsdk-fj0e3-455fdbfa00.json")
firebase_admin.initialize_app(cred)

# Inisialisasi Firestore
db = firestore.client()

# Data baru yang ingin ditambahkan
data_bengkels = [
    {
        "judul": "Perawatan Ban Sepeda Motor untuk Kestabilan dan Kenyamanan Berkendara",
        "isi": "Ban sepeda motor adalah salah satu komponen vital yang berperan penting dalam kenyamanan dan keselamatan berkendara. Untuk menjaga kestabilan dan kenyamanan saat berkendara, perawatan ban sangat diperlukan. Hal pertama yang perlu diperhatikan adalah tekanan angin. Pastikan tekanan ban sesuai dengan rekomendasi pabrikan untuk mencegah ban cepat aus dan meningkatkan efisiensi bahan bakar. Selain itu, periksa kedalaman alur pada ban. Jika alur sudah terlalu tipis, segera ganti ban untuk menghindari risiko selip dan kerusakan pada komponen sepeda motor lainnya. Dengan merawat ban secara rutin, Anda dapat meningkatkan kenyamanan serta keamanan saat berkendara di berbagai kondisi jalan."
    },
    {
        "judul": "Perawatan Rem Sepeda Motor untuk Keselamatan Berkendara",
        "isi": "Rem merupakan salah satu komponen yang sangat penting untuk menjaga keselamatan berkendara. Agar performa rem tetap optimal, penting untuk memeriksa kampas rem secara rutin. Kampas rem yang aus dapat memperpanjang jarak pengereman dan berpotensi menurunkan keamanan saat berkendara. Selain kampas, pastikan cairan rem dalam kondisi yang baik dan tidak mengalami kebocoran. Bila cairan rem sudah kotor atau berkurang, segera lakukan penggantian. Sebagai langkah tambahan, pastikan rem depan dan rem belakang bekerja secara seimbang untuk meningkatkan efisiensi pengereman dan mengurangi risiko terjadinya kecelakaan."
    },
    {
        "judul": "Perawatan Rantai Sepeda Motor agar Tidak Cepat Aus",
        "isi": "Rantai sepeda motor adalah komponen yang sering kali diabaikan, namun perannya sangat vital dalam kelancaran dan performa motor. Agar rantai tidak cepat aus, Anda perlu melumasi dan menyetelnya secara teratur. Rantai yang kendor dapat menyebabkan kerusakan pada komponen lain, seperti gear, sehingga berpengaruh pada performa mesin. Selain itu, bersihkan rantai secara berkala untuk menghindari kotoran yang menumpuk. Gunakan pelumas rantai yang sesuai untuk menjaga kinerja dan ketahanan rantai agar tidak cepat aus. Dengan merawat rantai secara rutin, Anda bisa memperpanjang usia pakai dan menjaga performa motor tetap optimal."
    },
    {
        "judul": "Perawatan Filter Udara untuk Meningkatkan Performa Mesin",
        "isi": "Filter udara berperan penting dalam memastikan aliran udara yang optimal ke mesin. Jika filter udara kotor, aliran udara terhambat, sehingga performa mesin menurun. Oleh karena itu, pastikan filter udara selalu bersih dan ganti secara berkala sesuai dengan rekomendasi pabrikan. Filter udara yang bersih akan meningkatkan efisiensi pembakaran, mengurangi konsumsi bahan bakar, dan menjaga performa mesin tetap optimal. Perawatan filter udara yang rutin akan berkontribusi pada performa mesin yang lebih baik, terutama saat motor digunakan dalam kondisi berkendara sehari-hari maupun saat menghadapi medan berat."
    },
    {
        "judul": "Perawatan Sistem Pembuangan Motor agar Mesin Tidak Tersendat",
        "isi": "Sistem pembuangan yang bersih sangat penting untuk menjaga performa mesin sepeda motor. Sistem pembuangan yang kotor dapat menyebabkan mesin tersendat, mengurangi efisiensi bahan bakar, dan menghasilkan emisi yang lebih tinggi. Pastikan knalpot dalam kondisi bebas dari kotoran dan kerusakan, seperti penyumbatan akibat karbon atau kerusakan lainnya. Sebisa mungkin, hindari penggunaan knalpot yang terlalu panjang atau tidak sesuai dengan spesifikasi pabrikan. Dengan menjaga sistem pembuangan tetap bersih dan sehat, Anda akan mendapatkan performa mesin yang optimal serta emisi yang lebih ramah lingkungan."
    },
    {
        "judul": "Perawatan Kabel dan Busi Sepeda Motor untuk Menghindari Mogok",
        "isi": "Kabel dan busi berfungsi sebagai penghubung utama antara sistem kelistrikan dan mesin. Jika kabel atau busi bermasalah, motor dapat mengalami mogok atau performa yang tidak stabil. Pastikan kabel tidak longgar dan busi dalam kondisi baik. Ganti busi secara teratur setiap beberapa ribu kilometer untuk menjaga pembakaran tetap optimal. Selain itu, pastikan sambungan kabel tidak mengalami korosi dan berfungsi dengan baik. Perawatan kabel dan busi yang rutin akan memastikan sistem kelistrikan bekerja dengan stabil dan mengurangi risiko mogok saat sedang berkendara."
    },
    {
        "judul": "Perawatan Sistem Kelistrikan Sepeda Motor untuk Kinerja yang Optimal",
        "isi": "Sistem kelistrikan berperan penting dalam menjaga seluruh komponen elektronik motor tetap berfungsi dengan optimal. Pastikan semua sambungan kabel dan terminal dalam kondisi baik dan bebas dari korosi. Cek secara rutin sistem kelistrikan, terutama komponen seperti aki, saklar, dan lampu, untuk memastikan semuanya bekerja dengan baik. Jika ada komponen yang rusak atau bermasalah, segera ganti untuk mencegah gangguan saat berkendara. Perawatan sistem kelistrikan yang tepat akan meningkatkan kenyamanan berkendara dan mencegah terjadinya kerusakan yang lebih parah."
    },
    {
        "judul": "Merawat Kopling Sepeda Motor agar Tetap Halus dan Awet",
        "isi": "Kopling sepeda motor sangat penting untuk kenyamanan dan efisiensi saat berkendara. Kopling yang halus akan mempermudah perpindahan gigi dan mengurangi keausan pada komponen lain seperti rantai dan gigi. Pastikan kampas kopling tidak aus dan cairan kopling dalam kondisi yang cukup. Jika kampas kopling habis, motor akan sulit berpindah gigi dan performa mesin menurun. Perawatan kopling yang teratur akan menjaga kenyamanan dan efisiensi berkendara, serta memperpanjang usia pakai komponen kopling."
    },
    {
        "judul": "Perawatan Suspensi Sepeda Motor untuk Kenyamanan dan Stabilitas",
        "isi": "Suspensi yang baik akan memberikan kenyamanan dan stabilitas saat berkendara, terutama saat melewati medan yang tidak rata. Pastikan suspensi tidak bocor dan minyak suspensi dalam kondisi yang cukup. Lakukan pemeriksaan rutin pada suspensi dan ganti jika ditemukan kebocoran atau penurunan performa. Suspensi yang optimal akan membantu menjaga kestabilan dan kenyamanan, sehingga berkendara di berbagai kondisi jalan menjadi lebih aman dan menyenangkan."
    },
    {
        "judul": "Perawatan Sistem Pendingin Sepeda Motor untuk Mencegah Overheat",
        "isi": "Sistem pendingin sepeda motor berfungsi untuk menjaga suhu mesin tetap stabil. Jika sistem pendingin bermasalah, mesin dapat mengalami overheat yang berbahaya. Pastikan cairan pendingin dalam kondisi yang cukup dan tidak bocor. Periksa secara rutin radiator dan kipas pendingin, serta bersihkan kotoran yang menempel di radiator. Dengan menjaga sistem pendingin tetap berfungsi dengan baik, Anda dapat mencegah kerusakan akibat panas berlebih dan menjaga performa mesin tetap optimal."
    },
    {
        "judul": "Perawatan Suku Cadang Sepeda Motor untuk Meningkatkan Usia Pakai",
        "isi": "Suku cadang yang aus dapat menyebabkan performa motor menurun dan meningkatkan risiko kerusakan. Oleh karena itu, penting untuk memeriksa suku cadang secara rutin dan menggantinya jika sudah aus. Perhatikan komponen seperti rantai, gir, kampas rem, dan kopling. Penggantian suku cadang yang tepat waktu akan memperpanjang usia pakai dan menjaga performa sepeda motor tetap optimal."
    },
    {
        "judul": "Perawatan Motor saat Musim Hujan untuk Mencegah Korosi dan Kerusakan",
        "isi": "Saat musim hujan, sepeda motor memerlukan perawatan khusus agar tidak mudah rusak akibat air dan kotoran yang menempel. Pastikan komponen seperti sistem pembuangan, rem, dan kabel dalam kondisi kering dan bersih dari air. Gunakan pelumas anti-korosi pada komponen yang rentan terkena air, seperti rantai dan kabel, untuk mencegah korosi. Dengan melakukan perawatan saat musim hujan, Anda dapat menjaga motor tetap awet dan nyaman digunakan di segala kondisi cuaca."
    },
    {
        "judul": "Merawat Suspensi Sepeda Motor untuk Kenyamanan dan Keamanan Berkendara",
        "isi": "Suspensi yang optimal akan memberikan kenyamanan dan menjaga stabilitas saat berkendara di berbagai medan. Pastikan suspensi tidak bocor dan minyak suspensi dalam kondisi cukup. Lakukan pemeriksaan rutin untuk mengganti komponen yang sudah aus atau rusak. Suspensi yang baik akan meningkatkan kenyamanan berkendara dan meminimalisir risiko terjadinya kecelakaan."
    },
    {
        "judul": "Merawat Filter Bahan Bakar untuk Efisiensi Bahan Bakar yang Lebih Baik",
        "isi": "Filter bahan bakar berperan dalam menjaga aliran bahan bakar yang bersih dan lancar. Jika filter bahan bakar kotor, aliran bahan bakar akan terhambat, yang menyebabkan mesin kurang efisien. Pastikan filter bahan bakar bersih dan ganti secara teratur. Dengan perawatan yang baik, filter bahan bakar yang bersih akan meningkatkan efisiensi bahan bakar dan memperpanjang umur mesin."
    },
    {
        "judul": "Perawatan Rem Cakram Sepeda Motor untuk Menghindari Rem Mengunci",
        "isi": "Rem cakram membutuhkan perhatian khusus agar tetap berfungsi optimal dan aman. Periksa kampas rem secara rutin dan pastikan cairan rem dalam kondisi yang cukup. Kampas rem yang aus dapat menyebabkan rem terkunci saat digunakan. Sebisa mungkin hindari penggunaan rem cakram yang berlebihan dan pastikan kampas rem tidak tipis. Dengan merawat sistem rem cakram secara rutin, Anda dapat meningkatkan keselamatan dan kenyamanan berkendara."
    },
    {
        "judul": "Merawat Busi Sepeda Motor untuk Membantu Pembakaran yang Optimal",
        "isi": "Busi yang dalam kondisi baik sangat penting untuk pembakaran yang efisien. Ganti busi secara rutin setiap beberapa ribu kilometer untuk memastikan pembakaran yang optimal. Busi yang kotor atau aus dapat menyebabkan mesin sulit dihidupkan dan menurunkan performa. Dengan merawat busi secara rutin, Anda bisa meningkatkan performa mesin dan efisiensi bahan bakar."
    },
    {
        "judul": "Perawatan Sistem Transmisi Sepeda Motor untuk Performa yang Halus dan Tahan Lama",
        "isi": "Sistem transmisi yang halus dan bekerja dengan baik sangat penting untuk perpindahan gigi yang optimal. Pastikan oli transmisi dalam kondisi cukup dan ganti jika sudah mulai kotor atau berkurang. Lakukan pemeriksaan rutin pada kopling dan sistem transmisi untuk mencegah keausan yang berlebihan. Dengan merawat sistem transmisi secara tepat, Anda bisa memastikan performa sepeda motor tetap halus dan tahan lama."
    },
    {
        "judul": "Merawat Motor Agar Tetap Aman dan Nyaman di Jalan",
        "isi": "Perawatan sepeda motor secara rutin akan menjaga motor tetap aman dan nyaman digunakan. Periksa komponen-komponen penting seperti rem, ban, suspensi, dan sistem kelistrikan untuk memastikan kinerja yang optimal. Jika ada komponen yang rusak atau aus, segera lakukan penggantian. Dengan merawat motor secara teratur, Anda dapat mencegah kerusakan yang lebih parah dan menjaga kenyamanan serta keselamatan berkendara."
    },
    {
        "judul": "Perawatan Motor Custom agar Tetap Menarik dan Berkinerja Baik",
        "isi": "Motor custom memerlukan perhatian khusus untuk menjaga tampilan dan performanya tetap optimal. Pastikan komponen seperti knalpot, rangka, dan sistem kelistrikan dalam kondisi baik. Perawatan rutin, termasuk membersihkan dan mengganti komponen yang aus, akan menjaga motor custom tetap menarik dan bertenaga. Dengan perawatan yang tepat, motor custom dapat tetap tampil stylish dan berkinerja dengan baik."
    }
]

# Tambahkan data ke Firestore
for bengkel in data_bengkels:
    doc_ref = db.collection("berita").document()
    doc_ref.set(bengkel)
    print(f"Data {bengkel['judul']} berhasil ditambahkan!")
 