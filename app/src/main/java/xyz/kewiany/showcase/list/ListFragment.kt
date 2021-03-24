package xyz.kewiany.showcase.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.utils.setStandardScreenMode

class ListFragment : Fragment(R.layout.list_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setStandardScreenMode()
    }
}