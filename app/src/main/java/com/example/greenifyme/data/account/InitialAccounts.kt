package com.example.greenifyme.data.account

import com.example.greenifyme.data.Account


fun main() {
    initialAccounts.forEach {
        println("Account(${it.accountId}, \"${it.name}\", \"${it.email}\", \"${it.password}\", points = ${it.points}),")
    }
}


val initialAccounts = listOf(
    Account(
        1,
        "John Doe",
        "doe@ex.com",
        hashPassword("myNameIsBond"),
        points = 149,
        createdAt = 1704336860000L
    ),
    Account(
        2,
        "Jane Smith",
        "smith@ex.com",
        hashPassword("password123"),
        points = 528,
        createdAt = 1704519860000L
    ),
    Account(
        3,
        "Michael Johnson",
        "michaelj@ex.com",
        hashPassword("abc123"),
        points = 226,
        createdAt = 1704624860000L
    ),
    Account(
        4,
        "Emily Brown",
        "emilyb@ex.com",
        hashPassword("securepassword"),
        points = 475,
        createdAt = 1704710860000L
    ),
    Account(
        5,
        "David Wilson",
        "davidw@ex.com",
        hashPassword("password456"),
        points = 258,
        createdAt = 1704760860000L
    ),
    Account(
        6,
        "Sarah Taylor",
        "saraht@ex.com",
        hashPassword("letmein"),
        points = 469,
        createdAt = 1704798860000L
    ),
    Account(
        7,
        "James Martinez",
        "jamesm@ex.com",
        hashPassword("qwerty"),
        points = 134,
        createdAt = 1704832860000L
    ),
    Account(
        8,
        "Jennifer Anderson",
        "janderson@ex.com",
        hashPassword("password"),
        points = 705,
        createdAt = 1704847860000L
    ),
    Account(
        9,
        "Matthew Thomas",
        "mthomas@ex.com",
        hashPassword("matthew123"),
        points = 1360,
        createdAt = 1704848860000L
    ),
    Account(
        10,
        "Jessica Garcia",
        "jessicag@ex.com",
        hashPassword("hello123"),
        points = 868,
        createdAt = 1704870860000L
    ),
    Account(
        11,
        "Christopher Lee",
        "chrisl@ex.com",
        hashPassword("iloveyou"),
        points = 13,
        createdAt = 1704891860000L
    ),
    Account(
        12,
        "Amanda Lewis",
        "amandal@ex.com",
        hashPassword("password789"),
        points = 431,
        createdAt = 1704892860000L
    ),
    Account(
        13,
        "Daniel Harris",
        "danielh@ex.com",
        hashPassword("daniel123"),
        points = 198,
        createdAt = 1704892860000L
    ),
    Account(
        14,
        "Maria Martinez",
        "mariam@ex.com",
        hashPassword("welcome"),
        points = 739,
        createdAt = 1704896860000L
    ),
    Account(
        15,
        "Robert Jackson",
        "robertj@ex.com",
        hashPassword("password"),
        points = 608,
        createdAt = 1704902860000L
    ),
    Account(
        16,
        "Linda Clark",
        "lindac@ex.com",
        hashPassword("linda123"),
        points = 417,
        createdAt = 1704916860000L
    ),
    Account(
        17,
        "Kevin Young",
        "keviny@ex.com",
        hashPassword("kevin123"),
        points = 1305,
        createdAt = 1704966860000L
    ),
    Account(
        18,
        "Patricia Hernandez",
        "patriciah@ex.com",
        hashPassword("pass123"),
        points = 319,
        createdAt = 1705016860000L
    ),
    Account(
        19,
        "Richard Allen",
        "richarda@ex.com",
        hashPassword("richard123"),
        points = 1355,
        createdAt = 1705035860000L
    ),
    Account(
        20,
        "Susan King",
        "susank@ex.com",
        hashPassword("susan123"),
        points = 444,
        createdAt = 1705038860000L
    ),
    Account(
        21,
        "Mark Davis",
        "markd@ex.com",
        hashPassword("mark123"),
        points = 305,
        createdAt = 1705070860000L
    ),
    Account(
        22,
        "Karen White",
        "karenw@ex.com",
        hashPassword("karen123"),
        points = 595,
        createdAt = 1705096860000L
    ),
    Account(
        23,
        "Steven Thompson",
        "stevent@ex.com",
        hashPassword("steven123"),
        points = 3016,
        createdAt = 1705104860000L
    ),
    Account(
        24,
        "Laura Martinez",
        "lauram@ex.com",
        hashPassword("laura123"),
        points = 1295,
        createdAt = 1705143860000L
    ),
    Account(
        25,
        "Jason Robinson",
        "jasonr@ex.com",
        hashPassword("jason123"),
        points = 353,
        createdAt = 1705343860000L
    ),
    Account(
        26,
        "Sandra Harris",
        "sandrah@ex.com",
        hashPassword("sandra123"),
        points = 446,
        createdAt = 1705359860000L
    ),
    Account(
        27,
        "Timothy Young",
        "timothyy@ex.com",
        hashPassword("timothy123"),
        points = 991,
        createdAt = 1705389860000L
    ),
    Account(
        28,
        "Cynthia Hall",
        "cynthiah@ex.com",
        hashPassword("cynthia123"),
        points = 238,
        createdAt = 1705464860000L
    ),
    Account(
        29,
        "Gregory Nelson",
        "gregoryn@ex.com",
        hashPassword("gregory123"),
        points = 1161,
        createdAt = 1705482860000L
    ),
    Account(
        30,
        "Angela King",
        "angelak@ex.com",
        hashPassword("angela123"),
        points = 387,
        createdAt = 1705483860000L
    ),
    Account(
        31,
        "Kenneth Martinez",
        "kennethm@ex.com",
        hashPassword("kenneth123"),
        points = 614,
        createdAt = 1705519860000L
    ),
    Account(
        32,
        "Tiffany Brown",
        "tiffanyb@ex.com",
        hashPassword("tiffany123"),
        points = 353,
        createdAt = 1705591860000L
    ),
    Account(
        33,
        "Bryan Scott",
        "bryans@ex.com",
        hashPassword("bryan123"),
        points = 2507,
        createdAt = 1705605860000L
    ),
    Account(
        34,
        "Pamela Lopez",
        "pamelal@ex.com",
        hashPassword("pamela123"),
        points = 1635,
        createdAt = 1705606860000L
    ),
    Account(
        35,
        "Peter Wright",
        "peterw@ex.com",
        hashPassword("peter123"),
        points = 1189,
        createdAt = 1705616860000L
    ),
    Account(
        36,
        "Deborah Carter",
        "deborahc@ex.com",
        hashPassword("deborah123"),
        points = 524,
        createdAt = 1706166860000L
    ),
    Account(
        37,
        "Jeremy Baker",
        "jeremyb@ex.com",
        hashPassword("jeremy123"),
        points = 267,
    ),
    Account(
        38,
        "Hannah Diaz",
        "hannahd@ex.com",
        hashPassword("hannah123"),
        points = 1488,
        createdAt = 1707608860000L
    ),
    Account(
        39,
        "Keith Ramirez",
        "keithr@ex.com",
        hashPassword("keith123"),
        points = 1023,
        createdAt = 1707840860000L
    ),
    Account(
        40,
        "Stephanie Reed",
        "stephanier@ex.com",
        hashPassword("stephanie123"),
        points = 937,
        createdAt = 1708460860000L
    ),
)