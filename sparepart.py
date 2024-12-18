import firebase_admin
from firebase_admin import credentials, firestore

# Inisialisasi Firebase
cred = credentials.Certificate("C:\\projek AKU\\dummyDataFirebase\\motofinder-2f3fb-firebase-adminsdk-fj0e3-455fdbfa00.json")
firebase_admin.initialize_app(cred)

# Inisialisasi Firestore
db = firestore.client()

# Data baru yang ingin ditambahkan
data_sparepart = [
   
    {
        "nama": "Selang Rem",
        "harga": 250000,
        "deskripsi": "Selang rem terbuat dari bahan karet berkualitas tinggi yang dilapisi serat stainless steel untuk ketahanan ekstra. Tersedia dalam berbagai pilihan warna, seperti hitam, merah, dan biru. Dirancang untuk tahan terhadap panas, tekanan tinggi, dan kompatibel dengan semua jenis cairan rem. Cocok untuk kendaraan harian maupun performa tinggi."
    },
    {
        "nama": "Kampas Rem",
        "harga": 150000,
        "deskripsi": "Kampas rem berkualitas tinggi dengan material semi-metallic, memberikan daya cengkeram optimal dan tahan lama. Cocok untuk semua jenis motor, baik harian maupun balap."
    },
    {
        "nama": "Rantai Motor",
        "harga": 300000,
        "deskripsi": "Rantai motor berbahan baja tahan karat, dirancang untuk durabilitas tinggi dan performa maksimal. Tersedia untuk berbagai ukuran dan jenis motor."
    },
    {
        "nama": "Busi Motor",
        "harga": 50000,
        "deskripsi": "Busi motor dengan elektroda berbahan iridium, memastikan pembakaran lebih efisien dan meningkatkan performa mesin. Cocok untuk motor standar maupun performa tinggi."
    },
    {
        "nama": "Aki Motor",
        "harga": 350000,
        "deskripsi": "Aki motor bebas perawatan (maintenance-free) dengan kapasitas daya tinggi, memberikan keandalan untuk semua jenis motor. Tersedia untuk motor matic, sport, dan bebek."
    },
    {
        "nama": "Spion Motor",
        "harga": 100000,
        "deskripsi": "Spion motor dengan desain stylish dan kaca anti-silau. Cocok untuk berbagai jenis motor dengan dudukan universal."
    },
    {
        "nama": "Handle Rem",
        "harga": 85000,
        "deskripsi": "Handle rem berbahan aluminium dengan desain ergonomis, nyaman digunakan, dan tersedia dalam berbagai warna."
    },
    {
        "nama": "Ban Luar Motor",
        "harga": 500000,
        "deskripsi": "Ban luar motor dengan teknologi radial untuk cengkeraman maksimal dan daya tahan tinggi. Tersedia untuk berbagai ukuran motor."
    },
    {
        "nama": "Ban Dalam Motor",
        "harga": 100000,
        "deskripsi": "Ban dalam motor berbahan karet elastis dengan ketahanan tinggi terhadap tekanan udara. Tersedia untuk berbagai ukuran ban."
    },
    {
        "nama": "Lampu LED Motor",
        "harga": 200000,
        "deskripsi": "Lampu LED motor dengan cahaya terang dan hemat energi. Cocok untuk semua jenis motor dengan dudukan standar."
    },
    {
        "nama": "Jok Motor",
        "harga": 300000,
        "deskripsi": "Jok motor empuk dengan bahan kulit sintetis berkualitas tinggi. Tersedia dalam berbagai desain dan warna."
    },
    {
        "nama": "Saringan Udara",
        "harga": 120000,
        "deskripsi": "Saringan udara berbahan spons dan serat mikro, memberikan sirkulasi udara optimal dan menjaga mesin tetap bersih."
    },
    {
        "nama": "Oli Mesin",
        "harga": 75000,
        "deskripsi": "Oli mesin berkualitas tinggi dengan teknologi sintetik untuk melindungi mesin dan meningkatkan performa motor."
    },
    {
        "nama": "Bearing Roda",
        "harga": 80000,
        "deskripsi": "Bearing roda dengan material baja berkualitas, memberikan putaran roda yang halus dan tahan lama."
    },
    {
        "nama": "Stang Motor",
        "harga": 250000,
        "deskripsi": "Stang motor berbahan aluminium ringan dengan desain sporty. Tersedia untuk berbagai jenis motor."
    },
    {
        "nama": "Shockbreaker",
        "harga": 400000,
        "deskripsi": "Shockbreaker motor dengan sistem hidrolik untuk kenyamanan berkendara. Cocok untuk motor harian dan touring."
    }

]

# Tambahkan data ke Firestore
for sparepart in data_sparepart:
    doc_ref = db.collection("sparepart").document()
    doc_ref.set(sparepart)
    print(f"Data {sparepart['nama']} berhasil ditambahkan!")
 