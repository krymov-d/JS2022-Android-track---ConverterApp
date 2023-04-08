package kz.kd.converterapp.chat

interface UserNameRepository {
    fun putUserNameToSharedPreference(name: String)
}