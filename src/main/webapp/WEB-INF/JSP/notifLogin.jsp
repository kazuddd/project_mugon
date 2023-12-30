<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MUGON Login Page</title>
    <link rel="stylesheet" type="text/css" href="Signup.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@300&display=swap">
</head>

<style>

    @import url('https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@300&display=swap');

    a{
        text-decoration: none;
    }
    body {
        font-family: Arial, sans-serif;
        background: linear-gradient(to bottom, #3F0071, #150050);
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    .Signup-container {
        font-size: 50px;
        background-color: #fff;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        max-width: 400px;
        margin-left: 100px;
        width: 400px;
        height: 500px;
        justify-content: center;
    }

    .Signup-container h2 {
        text-align: center;
        margin-bottom: 10px;
    }

    .Signup-form input[type="text"],
    .Signup-form input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .Signup-form button {
        width: 100%;
        padding: 10px;
        background-color: #6200ff;
        color: #fff;
        border: none;
        border-radius: 10px;
        cursor: pointer;
    }

    .Signup-form button:hover {
        background-color: #a200ff;
    }

    hr {
        border: none;
        height: 30px;
        background: transparent;
        margin: 0;
        padding: 0;
    }


    .mug-1 {
        width: 217px;
        height: 97px;
        position: absolute; /* Mengatur posisi absolut */
        top: 10%; /* Menggeser gambar ke tengah vertikal */
        transform: translateY(-50%); /* Menyesuaikan posisi gambar ke tengah vertikal */
    }

    .mug-2 {
        width: 1032px; /* Lebar gambar */
        height: 614px; /* Tinggi gambar */
    }

    .menu {
        display: flex;
        justify-content: space-between;
        font-family: 'Josefin Sans', sans-serif;
        font-weight: bolder;
    }

    .menu a {
        font-size: 30px; /* Ganti ukuran font sesuai keinginan Anda */
    }

    .Signup-form button[type= "submit"]{
        font-size: 20px;
        font-weight: bold;
        margin-top: 20px;
    }

    .next button{
        font-family: 'Josefin Sans', sans-serif;
        width: 100%;
        padding: 10px;
        background-color: #6200ff;
        color: #fff;
        border: none;
        border-radius: 10px;
        cursor: pointer;
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top: 2%;
        font-size: 50px;

    }

    .apahayo{
        margin-top: 3%;
    }

</style>


<body>
<img src="../asep/Group 6.png" alt="logo mugon" class="mug-1">
<div class="apahayo">

    <img src="../asep/Mugon Login.png" alt="mugon besar" class="mug-2">

    <div class="next">
        <a href="/menu">
            <button >Next</button>
        </a>
    </div>
</div>

</body>
</html>
