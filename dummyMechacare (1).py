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
        "nama": "Agung Jaya Motor",
        "alamat": "Jl. Jamin Ginting No.99, Darat, Kec. Medan Baru, Kota Medan, Sumatera Utara 20135",
        "koordinat": [3.5689217945564273, 98.66008548922218],
        "gmaps": "https://maps.app.goo.gl/15xP8eEHo8EEqdCcA"
    },
    {
        "nama": "Bengkel Honda Jaya",
        "alamat": "Jl. Jamin Ginting No.119, Darat, Kec. Medan Baru, Kota Medan, Sumatera Utara 20153",
        "koordinat": [3.5684554911731277, 98.66014022094822],
        "gmaps": "https://maps.app.goo.gl/gxird1sTXWtPbgHm6"
    },
    {

        "nama": "Masterban Jamin Ginting",
        "alamat": "Jl. Jamin Ginting No.90, Merdeka, Kec. Medan Baru, Kota Medan, Sumatera Utara 20222",
        "koordinat": [3.5692714488970116, 98.65978706760518],
        "gmaps": "https://maps.app.goo.gl/yjveZR7Y4uHs6GRw6"
    },
    {
        "nama": "Sinar Agung Motor",
        "alamat": "Jl. Setia Budi No.90 D, Tj. Rejo, Kec. Medan Sunggal, Kota Medan, Sumatera Utara 20122",
        "koordinat": [3.577605171866732, 98.64245327401574],
        "gmaps": "https://maps.app.goo.gl/DWzMNi9UE6eZWjpe6"
    },
    {
        "nama": "Utama Jaya",
        "alamat": "Jl. Setia Budi No.90, Tj. Rejo, Kec. Medan Sunggal, Kota Medan, Sumatera Utara 20154",
        "koordinat": [3.577461110528924, 98.64315660808195],
        "gmaps": "https://maps.app.goo.gl/nXEba1z2arqz33ye6"
    },
    {
        "nama": "Bengkel Harapan Maju",
        "alamat": "Jl. Setia Budi No.82, Tj. Rejo, Kec. Medan Sunggal, Kota Medan, Sumatera Utara 20154",
        "koordinat": [3.5775411263669774, 98.64275506390233],
        "gmaps": "https://maps.app.goo.gl/dXUPgdvr6Jse4skt8"
    },
    {
        "nama": "Saudara Jaya Motor",
        "alamat": "Jl. Setia Budi No.65K, Tj. Rejo, Kec. Medan Sunggal, Kota Medan, Sumatera Utara 20122",
        "koordinat": [3.5791373099983255, 98.6432915945897],
        "gmaps": "https://maps.app.goo.gl/dMHK3om2Ci8rfD436"
    },
    {
        "nama": "SUKARDI MOTOR",
        "alamat": "Jalan Setia budi",
        "koordinat": [3.579163909989917, 98.64325806575381],
        "gmaps": "https://maps.app.goo.gl/zLj8faqzFprDt9uU6"
    },
    {
        "nama": "TEGUH JAYA MOTOR",
        "alamat": "Jl. Setia Budi No.65, Tj. Rejo, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20122",
        "koordinat": [3.579391894098108, 98.6432871369179],
        "gmaps": "https://maps.app.goo.gl/aKMc3drrhThsnQfg9"
    },
    {
        "nama": "Setia Jaya Bullaes",
        "alamat": "Jl. Setia Budi No.62c, Tj. Rejo, Kec. Medan Sunggal, Kota Medan, Sumatera Utara 20122",
        "koordinat": [3.5796073019421466, 98.64290773691792],
        "gmaps": "https://maps.app.goo.gl/ZpcTwPLbBS1ApzXn9"
    }
]

# Tambahkan data ke Firestore
for bengkel in data_bengkels:
    doc_ref = db.collection("bengkel").document()
    doc_ref.set(bengkel)
    print(f"Data {bengkel['nama']} berhasil ditambahkan!")