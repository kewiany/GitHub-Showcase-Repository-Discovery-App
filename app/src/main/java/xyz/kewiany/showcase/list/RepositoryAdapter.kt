package xyz.kewiany.showcase.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.repository_item_view.view.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.entity.Repository
import xyz.kewiany.showcase.list.RepositoryAdapter.RepositoryViewHolder
import xyz.kewiany.showcase.utils.*

class RepositoryAdapter(
    private val onClick: (Long) -> Unit,
    private val items: MutableList<Repository> = mutableListOf()
) : RecyclerView.Adapter<RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repository_item_view, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) = holder.bind(items[position])

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(repository: Repository) = with(itemView) {
            repositoryNameTextView.text = repository.name
            repositoryDescriptionTextView.text = repository.description
            repositoryStarsTextView.text = repository.stars.toString()
            repositoryWatchersTextView.text = repository.watchers.toString()
            repositoryUpdatedAt.text = repository.updatedAt.toFormattedUpdatedDate(context)
            repositoryLanguageTextView.text = repository.language
            setOnClickListener { onClick(repository.id) }
        }
    }
}

private fun isSameHour(now: ZonedDateTime, dateToCheck: ZonedDateTime) = now.hour == dateToCheck.hour
fun isToday(now: LocalDate, dateToCheck: LocalDate): Boolean = now.isEqual(dateToCheck)

//TODO remove duplications

fun String.toFormattedUpdatedDate(context: Context): String? {
    if (isEmpty()) return ""
    val nowZonedDateTime = now.atZone(zoneID)
    val zonedDateTime = Instant.parse(this).atZone(zoneID)
    val localDateTime = zonedDateTime.toLocalDateTime()

    val isToday = isToday(nowZonedDateTime.toLocalDate(), zonedDateTime.toLocalDate())

    val (resId, formatter) = if (isToday && isSameHour(nowZonedDateTime, zonedDateTime)) {
        R.string.updated_minutes to todayMinutesFormatter
    } else if (isToday) {
        R.string.updated_hours to todayHoursFormatter
    } else {
        R.string.updated_on to dateFormatter
    }
    return context.getString(resId, localDateTime.format(formatter).format(formatter))
}

fun String.toFormattedCreatedDate(context: Context): String? {
    if (isEmpty()) return ""
    val nowZonedDateTime = now.atZone(zoneID)
    val zonedDateTime = Instant.parse(this).atZone(zoneID)
    val localDateTime = zonedDateTime.toLocalDateTime()

    val isToday = isToday(nowZonedDateTime.toLocalDate(), zonedDateTime.toLocalDate())

    val (resId, formatter) = if (isToday && isSameHour(nowZonedDateTime, zonedDateTime)) {
        R.string.created_minutes to todayMinutesFormatter
    } else if (isToday) {
        R.string.created_hours to todayHoursFormatter
    } else {
        R.string.created_on to dateFormatter
    }
    return context.getString(resId, localDateTime.format(formatter).format(formatter))
}