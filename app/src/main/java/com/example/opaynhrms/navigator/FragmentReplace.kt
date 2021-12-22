package com.example.opaynhrms.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ieltslearning.navigator.FragmentBehaviour

class FragmentReplace(val container: Int) : FragmentBehaviour() {
    override fun execute(transaction: FragmentTransaction, fragment: Fragment, tag: String?) {
        transaction.replace(container, fragment, tag)
    }

}