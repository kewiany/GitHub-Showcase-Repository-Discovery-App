package xyz.kewiany.showcase.details

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.follower_item_view.view.*
import xyz.kewiany.showcase.R

class FollowerItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var avatar: String? = null
        set(value) {
            field = value
            Glide.with(this)
                .load(avatar)
                .circleCrop()
                .into(followerImageView)
        }
    var name: String? = null
        set(value) {
            field = value
            followerTextView.text = value
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.follower_item_view, this, true)
    }
}