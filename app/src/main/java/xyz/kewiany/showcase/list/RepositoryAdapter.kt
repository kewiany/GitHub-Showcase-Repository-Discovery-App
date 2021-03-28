package xyz.kewiany.showcase.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.repository_item_view.view.*
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
            repositoryUpdatedAt.text = repository.toFormattedDate(context)
            repositoryLanguageTextView.text = repository.language
            setOnClickListener { onClick(repository.id) }
        }
    }
}

private fun isSameHour(now: ZonedDateTime, dateToCheck: ZonedDateTime) = now.hour == dateToCheck.hour
private fun isToday(now: LocalDate, dateToCheck: LocalDate): Boolean = now.isEqual(dateToCheck)

fun Repository.toFormattedDate(context: Context): String? {
    val nowZonedDateTime = now.atZone(zoneID)
    val updatedAtZonedDateTime = updatedAtInstant.atZone(zoneID)
    val updatedAtLocalDateTime = updatedAtZonedDateTime.toLocalDateTime()

    val isToday = isToday(nowZonedDateTime.toLocalDate(), updatedAtZonedDateTime.toLocalDate())

    val (resId, formatter) = if (isToday && isSameHour(nowZonedDateTime, updatedAtZonedDateTime)) {
        R.string.updated_minutes to todayMinutesFormatter
    } else if (isToday) {
        R.string.updated_hours to todayHoursFormatter
    } else {
        R.string.updated_on to dateFormatter
    }
    return context.getString(resId, updatedAtLocalDateTime.format(formatter).format(formatter))
}