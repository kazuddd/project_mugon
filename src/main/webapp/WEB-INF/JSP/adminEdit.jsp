<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form Jual MUGON</title>
    <link rel="stylesheet" href="jual.css">
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<style>

    @import url('https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@300&display=swap');

    body {
        font-family: 'Josefin Sans';
        margin: 0;
        padding: 0;
        font-weight: bold;
    }

    .Menu {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px 20px;
        background: linear-gradient(to bottom, #3F0071, #150050);
        color: #fff;
        position: relative; /* Membuat posisi relative untuk elemen ini */

    }

    .jual-form {
        display: flex;
        justify-content: space-between;
        flex-direction: row-reverse;
    }

    .search-bar{
        width: 800px;
        height: 40px;
        border-radius: 50px;
        position: absolute;
        top: 50%; /* Meletakkan di tengah vertikal */
        left: 50%; /* Meletakkan di tengah horizontal */
        transform: translate(-50%, -50%);
        z-index: 1;
    }

    .cart{
        position: absolute;
        top: 40%;
        left: 72%;
        z-index: 2;
    }

    .cart img {
        width: 30px;
    }

    .search-bar input {
        width: 800px;
        height: 35px;
        border: 5px solid white;
        border-radius: 50px;
        max-width: 800px;
    }

    .profile {
        display: flex;
        flex-direction: column;
        align-items: flex-end;
        margin-left: auto;
    }

    .profile img {
        width: 50px;
        margin-bottom: 5px;
        position: absolute;
        top: 30%;
        left: 76%;
        z-index: 3;
    }

    .logout a {
        color: #fff;
        text-decoration: none;
        margin-right: 50px;
        font-size: 20px;
        font-family: 'Josefin Sans', sans-serif;
    }

    .profile span1 {
        width: 200px;
        margin-bottom: 50px;
        position: absolute;
        top: 30%;
        left: 80%;
        z-index: 4;
        font-size: 20px;
        font-family: 'Josefin Sans', sans-serif;
    }
    .profile span2 {
        width: 200px;
        margin-bottom: 50px;
        position: absolute;
        top: 50%;
        left: 80%;
        z-index: 4;
        font-size: 20px;
        font-family: 'Josefin Sans', sans-serif;
    }

    /*  container untuk Jual */

    .jual-container {
        margin: auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin-top: 50px;
        width: 600px;
        height: 500px; /* Menggunakan tinggi otomatis */
    }

    .container1 {
        display: flex;
        flex-direction: column; /* Menggunakan flex-direction column */
        justify-content: space-between;
        align-items: center;
        padding: 2%;
        margin-top: 2%;
        height: 50px; /* Tinggi container1 diatur agar sejajar dengan gambar dan button */
    }

    .header {
        text-align: center;
        margin-bottom: 20px;
        font-weight: bold;
        font-family: 'Josefin Sans', sans-serif;
        font-size: 20px;
    }

    label {
        margin-bottom: 8px;
    }

    input, select {
        padding: 10px;
        margin-bottom: 15px;
        box-sizing: border-box;
    }

    button.kirim-button {
        background-color: #3F0071;
        color: #fff;
        border: solid #3F0071;
        border-radius: 4px;
        cursor: pointer;
        margin-top: 10px;
        width: 200px;
    }

    button.kirim-button:hover {
        background-color: greenyellow;
    }

    button.pilih-foto-button {
        background-color: #0071A4; /* Ubah warna sesuai kebutuhan Anda */
        color: #fff;
        border: solid #0071A4; /* Ubah warna sesuai kebutuhan Anda */
        border-radius: 4px;
        cursor: pointer;
        margin-top: 10px;
    }

    button.pilih-foto-button:hover {
        background-color: lightblue; /* Ubah warna sesuai kebutuhan Anda */
    }


    .profile span1 {
        width: 200px;
        margin-bottom: 50px;
        position: absolute;
        top: 30%;
        left: 80%;
        z-index: 4;
        font-size: 20px;
        font-family: 'Josefin Sans', sans-serif;
        color: white;
    }
    .profile span2 {
        width: 200px;
        margin-bottom: 50px;
        position: absolute;
        top: 50%;
        left: 80%;
        z-index: 4;
        font-size: 20px;
        font-family: 'Josefin Sans', sans-serif;
        color: white;
    }

    /*gambar*/

    .kotakgambar img {
        width: 300px;
        height: 300px;
    }

    .isi-gambar {
        display: flex;
        justify-content: space-between;
    }

    /* Atur margin dan padding untuk elemen di dalam container1 */
    .container1 > div {
        margin-bottom: 15px;

    }

    /* Atur lebar dan margin untuk input */
    .container1 input {
        width: 100%;
        margin-bottom: 10px;
    }

    label {
        color: #3F0071;
        font-family: 'Josefin Sans', sans-serif;
        font-size: 16px;
    }

    input {
        border: 2px solid #3F0071;
        border-radius: 4px;
    }

    .isi-gambar > div:last-child {
        margin-top: 15px;
    }

    .container1-radio {
        display: flex;
        justify-content: flex-start;
        align-items: center;
        width: 90px;
        gap: 10px;
        padding-left: 40%;
    }
    .container1-radio label {
        justify-content: center;

    }

    .container1-radio input {
        justify-content: center;
        height: 26px;
    }

    .container1-radio div {
        display: flex;
        flex-direction: column;
        align-items: center;
        height: 50px;

    }

    .container12-radio {
        display: flex;
        align-items: center;
        justify-content: flex-start;
        width: 90px;
        gap: 10px;
        padding-left: 39%;
    }
    .container12-radio label {
        justify-content: center;

    }

    .container12-radio input {
        justify-content: center;
        height: 26px;
    }

    .container12-radio div {
        display: flex;
        flex-direction: column;
        align-items: center;
        height: 50px;

    }



</style>





<body class="h-[100vh] bg-gradient-to-b from-[#3F0071] to-[#150050]">
<div class="Menu">
    <div class="logo">
        <a href="/menu"><img src="../asep/Group 6.png" alt="Logo Mugon" class="mug-1"/></a>
    </div>
    <div class="profile">
        <a href="/profile"> <img src="../asep/profiledef.png" alt="Profil Anda"/></a>
        <a href="/topup"><span1>${loggedInUser.nama}</span1></a>
        <a href="/topup"><span2>Rp. ${loggedInUser.saldo}</span2></a>
    </div>
    <div class="search-bar">
        <input type="text" placeholder="Cari produk...">
    </div>
    <div class="cart">
        <a href="/keranjang"><img src="../asep/keranjang.png" alt="Keranjang Belanja"/></a>
    </div>

    <div class="logout">
        <a id="logout" href="/">Logout</a>
    </div>
</div>

<div class="jual-form">
    <img src="../asep/Group 12.png" alt="Gambar Anda" class="mug-2">
    <div class="jual-container">
        <div class="header">
            <h2>Edit Iklan</h2>
        </div>


        <form method="post" action="/admin/AdminEdit">
            <div class="container1">
                <div>
                    <input type="text" id="namabarang" name="namabarang" required placeholder="Nama Barang">
                </div>
            </div>

            <div class="container1">
                <div>
                    <input type="text" id="lokasibarang" name="lokasibarang" required placeholder="Lokasi Barang">
                </div>
            </div>

            <div class="container12-radio">
                <div>

                    <input type="radio" id="tipebarang" name="tipebarang" required placeholder="Tipe Barang" value="Tiup">
                    <label for="baru">Tiup</label>
                </div>

                <div>

                    <input type="radio" id="tipebarang" name="tipebarang" required placeholder="Tipe Barang" value="Petik">
                    <label for="baru">Petik</label>
                </div>

                <div>

                    <input type="radio" id="tipebarang" name="tipebarang" required placeholder="Tipe Barang" value="Tabuh">
                    <label for="bekas">Tabuh</label>
                </div>
            </div>


            <div class="container1">
                <button class="kirim-button">Sumbit</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
