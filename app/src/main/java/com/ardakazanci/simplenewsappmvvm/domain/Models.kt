package com.ardakazanci.simplenewsappmvvm.domain

/*

"author": "Stan Schroeder",
"title": "Bitcoin whale moves $1.1 billion in bitcoins for an $80 fee",
"description": "Bitcoin hasn't (yet) fulfilled its original promise of being widely-used electronic cash, but it still offers some features that would be hard to achieve within the traditional banking system. Namely, moving $1.1 billion from one address to another, in a sing…",
"url": "https://mashable.com/article/bitcoin-1-1-billion-transaction/",
"urlToImage": "https://mondrian.mashable.com/2020%252F01%252F15%252F38%252Fd26e834787934c56a33fdeb39faa0be8.84e34.jpg%252F1200x630.jpg?signature=IHj6xz7nTFxvmjn6XOvUiHKJCIM=",
"publishedAt": "2020-01-15T09:10:59Z",
"content"


*/


data class DomainModel(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)