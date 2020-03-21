package com.excalibur.funwithlist.pack5

import android.content.ContentResolver
import android.database.Cursor
import com.excalibur.funwithlist.MyApp
import java.lang.reflect.Field

val resolver: ContentResolver = MyApp.self().contentResolver

interface MediaUtils : ActionTask {

    fun <T : MediaModelBase> getMedia(
        clz: Class<T>,
        x: Boolean = true,
        projection: Array<String>? = null,
        selection: String? = null,
        selectArgs: Array<String>? = null,
        sortOrder: String? = null,
        onLoadPerItem: (item: T) -> Unit = {},
        onLoadTempList: (listItem: MutableList<T>) -> Unit = {}
    ): MutableList<T> {
        val media = clz.newInstance()
        val uri =
            if (x) {
                media.getUri()
            } else {
                media.getUriInternal()
            }
        val cursor = resolver.query(
            uri,
            projection,
            selection,
            selectArgs,
            sortOrder
        )
        val data = mutableListOf<T>()
        cursor?.moveToFirst()
        while (cursor?.isAfterLast == false) {
            val item = getRow(cursor, clz)
            data.add(item)
            onLoadPerItem(item)
            cursor.moveToNext()
        }
        return data
    }


    private fun <T : MediaModelBase> getRow(cursor: Cursor?, clz: Class<T>): T {
        val t = clz.newInstance()
        val fields = t.javaClass.declaredFields
        fields.forEach {
            it.isAccessible = true
            val annotation = it.getAnnotation(MediaInfo::class.java)
            val index = cursor?.getColumnIndex(annotation.getFieldName)
            mappingField(cursor!!, index!!, it, t)
        }
        return t
    }

    private fun <T : MediaModelBase> mappingField(
        cursor: Cursor,
        index: Int, f: Field, t: T
    ) {
        when (f.type) {
            Int::class.java -> f.setInt(t, cursor.getInt(index))
            String::class.java -> f.set(t, cursor.getString(index))
            Float::class.java -> f.setFloat(t, cursor.getFloat(index))
            Long::class.java -> f.setLong(t, cursor.getLong(index))
        }
    }

}