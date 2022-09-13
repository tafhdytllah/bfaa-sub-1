package com.tafh.githubuserapp.data.model

import User
import com.tafh.githubuserapp.R

object UserData {

    val listData: ArrayList<User>
        get() {
            val list = arrayListOf<User>()

            for (i in usernameData.indices) {
                val user = User()
                user.apply {
                    username = usernameData[i]
                    name = nameData[i]
                    avatar = avatarData[i]
                }
                list.add(user)
            }

            return list
        }

    private val usernameData = arrayOf(
        "bigtiger239",
        "purpleleopard272",
        "purplewolf989",
        "organickoala820",
        "orangepanda797",
        "goldenfish109",
        "blueswan165",
        "crazycat938",
        "bluebear787",
        "blueelephant373",
        "heavywolf560",
        "smallleopard973",
        "biglion964",
        "goldenbutterfly133",
        "bigsnake641",
        "silverfrog877",
        "heavyrabbit592",
        "brownrabbit758",
        "purplebear824",
        "redrabbit322"
    )

    private val nameData = arrayOf(
        "Peter Bell",
        "Alyssa Andre",
        "Natércio Castro",
        "Terrence King",
        "William David",
        "Irene Morales",
        "Stefan Siebert",
        "Amy Miller",
        "Noélie Roux",
        "Enrique Brewer",
        "Abssilão Rodrigues",
        "Liam Walters",
        "Nicole Curtis",
        "Deolindo Vieira",
        "Miguel Sanz",
        "Karen Stanley",
        "Tammy Hayes",
        "Hector Guerrero",
        "Julienne Lelieveld",
        "Aurélien Fontai"
    )

    private val avatarData = intArrayOf(
        R.drawable.avatar1,
        R.drawable.avatar2,
        R.drawable.avatar3,
        R.drawable.avatar4,
        R.drawable.avatar5,
        R.drawable.avatar6,
        R.drawable.avatar7,
        R.drawable.avatar8,
        R.drawable.avatar9,
        R.drawable.avatar10,
        R.drawable.avatar11,
        R.drawable.avatar12,
        R.drawable.avatar13,
        R.drawable.avatar14,
        R.drawable.avatar15,
        R.drawable.avatar16,
        R.drawable.avatar17,
        R.drawable.avatar18,
        R.drawable.avatar19,
        R.drawable.avatar20
    )
}