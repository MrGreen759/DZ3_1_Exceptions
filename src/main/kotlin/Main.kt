package ru.netology.dz3_1_exceptions

fun main(args: Array<String>) {

    // создаем пост 1
    var newCommentsInfo = Comments(1)
    var newLike = Like(1, true, false)
    var newPostSource = PostSource(platform = "android", data = "profile_photo")
    var newRepostsInfo = RepostsInfo(12)
    var newPost = Post(comments = newCommentsInfo,
        likes = newLike,
        postSource = newPostSource,
        reposts = newRepostsInfo)
    WallService.addPost(newPost)
    WallService.printPosts()

    // создаем пост 2
    newCommentsInfo = Comments(10)
    newLike = Like(10, true, false)
    newPost = Post(comments = newCommentsInfo,
        likes = newLike,
        postSource = newPostSource,
        reposts = newRepostsInfo)
    WallService.addPost(newPost)
    WallService.printPosts()

    // создаем пост 3
    newCommentsInfo = Comments(20)
    newLike = Like(20, true, true)
    newPost = Post(comments = newCommentsInfo,
        likes = newLike,
        postSource = newPostSource,
        reposts = newRepostsInfo,
        views = 144,
        attachments = arrayOf(GiftAttachment(Gift(1, "t1", "t2", "t3")),
            GraffityAttachment(Graffity(1, 2, "url", 400, 400)))
    )
    WallService.addPost(newPost)
    WallService.printPosts()

    // обновляем пост 2 (меняем количество комментариев, лайков и просмотров)
    newCommentsInfo = Comments(100)
    newLike = Like(30, userLikes = true)
    newPost = Post(2,
        comments = newCommentsInfo,
        likes = newLike,
        postSource = newPostSource,
        reposts = newRepostsInfo,
        views = 214)
    if (!WallService.updatePost(newPost)) println("Пост не найден.")
    WallService.printPosts()

    // добавляем комментарий к существующему посту
    var newComment = Comment(345, 456, "15.09.2022", "This is comment")
    WallService.createComment(2, newComment)

    // добавляем комментарий к несуществующему посту
    newComment = Comment(345, 456, "15.09.2022", "This is comment")
    WallService.createComment(4, newComment)

}

object WallService {
    private var posts = emptyArray<Post>()
    private var lastPostId: Int = 0
    private var comments = emptyArray<Comment>()

    // добавление поста в хранилище
    fun addPost (post: Post): Post {
        lastPostId++
        println("\nСоздаем пост. Номер поста - $lastPostId.")
        posts += post.copy(id = lastPostId)
        return posts.last()
    }

    // внесение изменений в существующий пост
    fun updatePost (post: Post): Boolean {
        println("\nОбновляем пост #${post.id}")
        for ((index) in posts.withIndex()) {
            if (posts[index].id == post.id) {
                val old = posts[index]
                posts[index] = post.copy(publicDate = old.publicDate, ownerId = old.ownerId)
                return true
            }
        }
        return false
    }

    // создание комментария
    fun createComment(postId: Int, comment: Comment): Comment {
        for (post in posts) {
            if (post.id == postId) {
                comments += comment
                return comment
            }
        }
        throw PostNotFoundException("Поста с ID $postId не существует.")
    }

    // проверочная печать массива постов
    fun printPosts() {
        for (post in posts) {
            println("----------------")
            println("ID поста: ${post.id}, ID автора: ${post.ownerId}, опубликовано: ${post.publicDate}")
            println("Текст поста: ${post.content}")
            println("Комментариев: ${post.comments.count}, лайков: ${post.likes.count}, просмотров: ${post.views}")
        }
    }

    fun clear() {
        posts = emptyArray()
        lastPostId = 0
        comments = emptyArray()
    }

}

data class Post (
    val id: Int = 0,                    // id поста
    val ownerId: Int = 12345,           // id владельца стены
    val fromId: Int = 123,                    // id автора поста
    val createdBy: Int = 12,                  // id администратора
    val publicDate: String = "12.09.2022", // дата публикации
    val content: String = "It's test content", // текст поста
    val replyOwnerId: Int? = null,              // id автора поста, в ответ на который создан пост
    val replyPostId: Int? = null,               // id поста, в ответ на который создан пост
    val friendsOnly: Boolean = false,   // только для друзей
    val comments: Comments,              // комментарии
    val copyright: Copyright? = null,           // источник информации
    val likes: Like,                    // лайки
    val reposts: RepostsInfo,           // инфо о репостах
    val views: Int = 124,               // просмотры
    val postType: String = "post",               // тип поста
    val postSource: PostSource,         // способ размещения записи
    val geo: Geo? = null,                       // местоположение
    val signerId: Int? =null,                  // id автора, если пост из сообщества
    val copyHistory: Array<Post>? = null,       // история репостов
    val canPin: Boolean = true,                // закрепление
    val canDelete: Boolean = false,             // удаление
    val canEdit: Boolean = false,               // редактирование
    val isPinned: Boolean = false,              // закреплен ли пост
    val isAds: Boolean = false,         // реклама ли
    val isFavorite: Boolean = false,            // в закладках ли
    val donut: Donut? = null,                   // VK Donut
    val postponed: Boolean = false,      // отложенный пост
    val attachments: Array<Attachment> = emptyArray()   // вложения
)

data class Comments (
    val count: Int = 0,
    val canComment: Boolean = true,
    val groupsCanComment: Boolean = true,
    val canClose: Boolean = false,
    val canOpen: Boolean = true,
)

data class Comment (
    val id: Int,
    val fromId: Int,
    val date: String,
    val text: String
)

data class Copyright (
    val id: Int,
    val link: String,
    val name: String,
    val type: String
)

data class Like (
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean = true,
    val canRepost: Boolean = true
)

data class RepostsInfo (
    val count: Int = 0,
    val userReposted: Boolean = false
)

data class PostSource (
    val type: String = "vk",
    val platform: String,
    val data: String,
    val url: String? = null
)

data class Geo (
    val type: String = "city",
    val coordinates: String? = null,
    val place: Place? = null
)

data class Place (
    val id: Int,
    val title: String,
    val latitude: Int,
    val longtitude: Int,
    val created: String,
    val icon: Int,
    val checkins: Int,
    val updated: String,
    val type: Int,
    val country: Int,
    val city: Int,
    val address: String
)

data class Donut (
    val isDonut: Boolean = false,
    val paidDuration: Int,
    val placeHolder: String,
    val canOpenForAll: Boolean = false,
    val editMode: String,
)

