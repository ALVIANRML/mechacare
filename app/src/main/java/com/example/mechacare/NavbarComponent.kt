package com.example.mechacare

import android.content.Context
import android.content.Intent
import android.view.View
import com.example.mechacare.ui.SparepartActivity

class NavbarComponent (context: Context, navbar: View) {
    init {

        val homeButton: View = navbar.findViewById(R.id.nav_home)
        val shopButton: View = navbar.findViewById(R.id.nav_shop)
        val profileButton: View = navbar.findViewById(R.id.nav_profile)

        homeButton.setOnClickListener {
            val intent = Intent(context, activity_welcome::class.java)
            context.startActivity(intent)
        }

        shopButton.setOnClickListener {
            val intent = Intent(context, SparepartActivity::class.java)
            context.startActivity(intent)
        }
        profileButton.setOnClickListener {
            val intent = Intent(context, Profile::class.java)
            context.startActivity(intent)
        }
    }
}