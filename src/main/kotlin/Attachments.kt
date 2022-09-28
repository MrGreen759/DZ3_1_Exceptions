package ru.netology.dz3_1_exceptions

interface Attachment {
    val type: String
}

class AudioAttachment (
    val audio: Audio, override val type: String = "Audio"
) : Attachment

data class Audio (
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int,
    val albumId: Int,
    val genreId: Int,
    val date: Int,
    val noSearch: Boolean,
    val isHQ:Boolean
)

class FileAttachment (
    val file: File, override val type: String = "File"
) : Attachment

data class File (
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int,
    val ext: String,
    val url: String,
    val date: String,
    val type: Int
)

class PhotoAttachment (
    val photo: Photo, override val type: String = "Photo"
) : Attachment

data class Photo (
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String,
    val date: String,
    val size: Int
)

class GiftAttachment (
    val gift: Gift, override val type: String = "Gift"
) : Attachment

data class Gift (
    val id: Int,
    val thumb256: String,
    val thumb96: String,
    val thumb48: String,
)

class GraffityAttachment (
    val graffity: Graffity, override val type: String = "Graffity"
) : Attachment

data class Graffity (
    val id: Int,
    val ownerId: Int,
    val url: String,
    val width: Int,
    val height: Int
)
