<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Penjualan</title>
    <link rel="stylesheet" type="text/css" href="Menu.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@300&display=swap">
</head>

<style>
    /* Styling header */


    @import url('https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@300&display=swap');


    body{
        margin: 0;
        padding: 0;

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

    .labelInspek {
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #3F0071;
        height: 80px; /* Adjust the height as needed */
        width: 40%; /* Set the width as needed */
        margin: 0 auto; /* Center horizontally */
        border-radius: 20px;
    }

    .labelInspek h1 {
        color: #000000;
        font-size: 24px
    }

    .mug-1 {
        width: 100px;
        margin-left: 50px;
        width: 217px;
        height: 97px;
    }

    .header-content {
        display: flex;
        align-items: center;
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

    .search-bar input {
        width: 800px;
        height: 35px;
        border: 5px solid white;
        border-radius: 50px;
        max-width: 800px;
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

    .profile h3 {
        width: 200px;
        margin-bottom: 50px;
        position: absolute;
        top: 30%;
        left: 80%;
        z-index: 4;
        font-size: 20px;
        font-family: 'Josefin Sans', sans-serif;
    }

    /* Styling main content */
    main {
        padding: 20px;
    }

    .iklan {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
    }

    .iklan-box {
        width: 30%;
        border: 5px solid #bbb9b9;
        padding: 10px;
        text-align: left;
        background-color: white;
        margin-top: 5%;
        width: 200px;
        margin-left: 10%;
        box-shadow: 1px 7px 10px 4px grey;
        border-radius: 10px;
    }

    /* Styling logout link */
    .logout a {
        color: #fff;
        text-decoration: none;
        margin-right: 50px;
        font-size: 20px;
        font-family: 'Josefin Sans', sans-serif;
    }

    .overlay {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5); /* Memberikan efek transparansi */
        z-index: 999; /* Menentukan urutan tumpukan untuk overlay */
        font-size: 20px;
        font-family: 'Josefin Sans', sans-serif;
        font-weight: bold;

    }

    .confirm-box {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: #321266;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        text-align: center;
    }

    .confirm-box p {
        font-size: 18px;
        margin-bottom: 20px;
        font-size: 30px;
        font-family: 'Josefin Sans', sans-serif;
        font-weight: bold;
    }

    .confirm-box button {
        padding: 10px 20px;
        background-color: #8032FF;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        margin: 0 10px;
    }

    .confirm-box p {
        font-size: 30px;
        margin-bottom: 20px;
        color: #fff; /* Ganti dengan kode warna yang Anda inginkan, contoh: merah (#FF0000) */
    }


    /* Additional styles as needed */

</style>

<body>
<div class="Menu">
    <div class="logo">
        <a href="/menu"><img src="../asep/Group 6.png" alt="Logo Mugon" class="mug-1"/></a>
    </div>
    <div class="profile">
        <a href="../Profile/Profile.html"> <img src="../asep/profile.png" alt="Profil Anda"/></a>
        <a href="../Topup/Topup.html"><span1>Ridho Udin</span1></a>
        <a href="../Topup/Topup.html"><span2>Rp. 20.000.000</span2></a>
    </div>
    <div class="search-bar">
        <input type="text" placeholder="Cari produk...">
    </div>
    <div class="cart">
        <a href="../Keranjang/Keranjang.html"><img src="../asep/keranjang.png" alt="Keranjang Belanja"/></a>
    </div>

    <div class="logout">
        <a id="logout" href="/">Logout</a>
    </div>
</div>
<main>
    <section class="iklan">
        <div class="iklan-box">
            <img src="product1.jpg" alt="Produk 1">
            <h3>Nama Produk 1</h3>
            <p>Harga: $50.00</p>
            <p>Detail: Deskripsi produk ini.</p>
            <p>Lokasi: Kota Anda</p>
        </div>
        <!-- Tambahkan lebih banyak iklan box sesuai kebutuhan -->
    </section>

    <section class="iklan">
        <div class="iklan-box">
            <img src="product1.jpg" alt="Produk 2">
            <h3>Nama Produk 2</h3>
            <p>Harga: $50.00</p>
            <p>Detail: Deskripsi produk ini.</p>
            <p>Lokasi: Kota Anda</p>
        </div>
        <!-- Tambahkan lebih banyak iklan box sesuai kebutuhan -->
    </section>

    <section class="iklan">
        <div class="iklan-box">
            <img src="product1.jpg" alt="Produk 3">
            <h3>Nama Produk 3</h3>
            <p>Harga: $50.00</p>
            <p>Detail: Deskripsi produk ini.</p>
            <p>Lokasi: Kota Anda</p>
        </div>
        <!-- Tambahkan lebih banyak iklan box sesuai kebutuhan -->
    </section>
</main>

<div class="overlay" id="logoutOverlay">
    <div class="confirm-box">
        <p>Yakin ingin logout?</p>
        <button id="confirmLogout">Y</button>
        <button id="cancelLogout">N</button>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        $("#logout").click(function() {
            $("#logoutOverlay").fadeIn();
        });

        $("#cancelLogout").click(function() {
            $("#logoutOverlay").fadeOut();
        });

        $("#confirmLogout").click(function() {
            // Gantilah dengan URL halaman login yang sesungguhnya
            window.location.href = "http://127.0.0.1:5500/Login/login.html";
        });
    });
</script>


</body>
</html>
