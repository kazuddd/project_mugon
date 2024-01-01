<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Top Up MUGON</title>
    <link rel="stylesheet" href="Topup.css">
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

    .Topup-form {
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

    /*  container untuk top up */

    .Topup-container {
        margin: auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin-top: 50px;
        width: 700px;
        height: 500px;
    }

    .container1 {
        background-color: #d7d3d3;
        display:flex;
        justify-content: space-between; /* Center the items vertically */
        align-items: center;
        padding:30px 16px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 350px;
        height: 20px;
        margin-top: 15px;
    }

    .container1 button {
        display: flex;
        border-radius: 12px;
        background-color: #3F0071;
        padding: 3px 15px;
        align-items: center;
        color: white;
        font-weight: bold;
    }

    .kepala {
        text-align: center;
        margin-bottom: 20px;
        font-weight: bold;
        font-size: 10px;
    }

    form {
        display: flex;
        flex-direction: column;
    }

    label {
        margin-bottom: 8px;
    }

    input, select {
        padding: 10px;
        margin-bottom: 15px;
        box-sizing: border-box;
    }

    button {
        background-color: #171a21;
        color: #fff;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    button:hover {
        background-color: #0d1016;

    }

    #result {
        margin-top: 20px;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    .mug-2 {
        width: 1032px; /* Lebar gambar */
        height: 614px; /* Tinggi gambar */
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

<div = class="Topup-form">
<img src="../asep/Group 12.png" alt="Gambar Anda" class="mug-2">
    <div class="Topup-container">
        <div class="kepala">
            <h2>Ayo Top Up saldomu agar mudah bertransaksi</h2>
        </div>
        <form action="/UpdateSaldo" method="post">
            <div class="container1">
                <div>
                    <h3>50.000</h3>
                    <h4>Minimum Topup</h4>
                    <input type="hidden" name="topupAmount" value="50000">
                </div>
                <button type="submit">Tambahkan</button>
            </div>
        </form>
        <form action="/UpdateSaldo" method="post">
            <div class="container1">
                <h3>100.000</h3>
                <input type="hidden" name="topupAmount" value="100000">
                <button type="submit">Tambahkan</button>
            </div>
        </form>

        <form action="/UpdateSaldo" method="post">
            <div class="container1">
                <h3>250.000</h3>
                <input type="hidden" name="topupAmount" value="250000">
                <button type="submit">Tambahkan</button>
            </div>
        </form>

        <form action="/UpdateSaldo" method="post">
            <div class="container1">
                <h3>1.000.000</h3>
                <input type="hidden" name="topupAmount" value="1000000">
                <button type="submit">Tambahkan</button>
            </div>
        </form>

        <form action="/UpdateSaldo" method="post">
            <div class="container1">
                <h3>Manual input</h3>
                <input type="text" name="topupAmount" required>
                <button type="submit">Tambahkan</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
