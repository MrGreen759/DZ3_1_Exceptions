package ru.netology.dz3_1_exceptions

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addPost() {
        val newCommentInfo = Comments(1)
        val newLike = Like(1, true, false)
        var newPostSource = PostSource(platform = "android", data = "profile_photo")
        var newRepostsInfo = RepostsInfo(12)
        var newPost = Post(comments = newCommentInfo,
            likes = newLike,
            postSource = newPostSource,
            reposts = newRepostsInfo)
        val result = WallService.addPost(newPost).id

        assertEquals(1, result)
    }

    @Test
    fun updatePostSuccess() {
        var newCommentInfo = Comments(1)
        var newLike = Like(1, true, false)
        var newPostSource = PostSource(platform = "android", data = "profile_photo")
        var newRepostsInfo = RepostsInfo(12)
        var newPost = Post(comments = newCommentInfo,
            likes = newLike,
            postSource = newPostSource,
            reposts = newRepostsInfo)
        WallService.addPost(newPost)

        newCommentInfo = Comments(5)
        newLike = Like(4, true, false)
        newPostSource = PostSource(platform = "android", data = "profile_photo")
        newRepostsInfo = RepostsInfo(12)
        newPost = Post(comments = newCommentInfo,
            likes = newLike,
            postSource = newPostSource,
            reposts = newRepostsInfo)
        WallService.addPost(newPost)

        newPost = Post(id = 1, comments = newCommentInfo, likes = newLike, postSource = newPostSource,
            reposts = newRepostsInfo, views = 21)
        val result = WallService.updatePost(newPost)

        assertTrue(result)

    }

    @Test
    fun updatePostFailure() {
        var newCommentInfo = Comments(1)
        var newLike = Like(1, true, false)
        var newPostSource = PostSource(platform = "android", data = "profile_photo")
        var newRepostsInfo = RepostsInfo(12)
        var newPost = Post(comments = newCommentInfo,
            likes = newLike,
            postSource = newPostSource,
            reposts = newRepostsInfo)
        WallService.addPost(newPost)

        newCommentInfo = Comments(5)
        newLike = Like(4, true, false)
        newPostSource = PostSource(platform = "android", data = "profile_photo")
        newRepostsInfo = RepostsInfo(12)
        newPost = Post(comments = newCommentInfo,
            likes = newLike,
            postSource = newPostSource,
            reposts = newRepostsInfo)
        WallService.addPost(newPost)

        newPost = Post(id = 3, comments = newCommentInfo, likes = newLike, postSource = newPostSource,
            reposts = newRepostsInfo,views = 21)
        val result = WallService.updatePost(newPost)

        assertFalse(result)
    }

    @Test
    fun createCommentSuccess() {
        var newCommentInfo = Comments(1)
        var newLike = Like(1, true, false)
        var newPostSource = PostSource(platform = "android", data = "profile_photo")
        var newRepostsInfo = RepostsInfo(12)
        var newPost = Post(comments = newCommentInfo,
            likes = newLike,
            postSource = newPostSource,
            reposts = newRepostsInfo)
        WallService.addPost(newPost)

        newCommentInfo = Comments(5)
        newLike = Like(4, true, false)
        newPostSource = PostSource(platform = "android", data = "profile_photo")
        newRepostsInfo = RepostsInfo(12)
        newPost = Post(comments = newCommentInfo,
            likes = newLike,
            postSource = newPostSource,
            reposts = newRepostsInfo)
        WallService.addPost(newPost)

        val newComment = Comment(345, 456, "15.09.2022", "This is comment")
        assertSame(newComment, WallService.createComment(2, newComment))
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        var newCommentInfo = Comments(1)
        var newLike = Like(1, true, false)
        var newPostSource = PostSource(platform = "android", data = "profile_photo")
        var newRepostsInfo = RepostsInfo(12)
        var newPost = Post(comments = newCommentInfo,
            likes = newLike,
            postSource = newPostSource,
            reposts = newRepostsInfo)
        WallService.addPost(newPost)

        newCommentInfo = Comments(5)
        newLike = Like(4, true, false)
        newPostSource = PostSource(platform = "android", data = "profile_photo")
        newRepostsInfo = RepostsInfo(12)
        newPost = Post(comments = newCommentInfo,
            likes = newLike,
            postSource = newPostSource,
            reposts = newRepostsInfo)
        WallService.addPost(newPost)

        val newComment = Comment(345, 456, "15.09.2022", "This is comment")
        WallService.createComment(3, newComment)
    }

}