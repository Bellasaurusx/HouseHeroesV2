import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChoreAdapter(
    private val chores: MutableList<Chore>,
    private val onTaskClicked: (Chore, Int) -> Unit
) : RecyclerView.Adapter<ChoreAdapter.ChoreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chore_item, parent, false)
        return ChoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChoreViewHolder, position: Int) {
        holder.bind(chores[position], position)
    }

    override fun getItemCount() = chores.size

    inner class ChoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskName: TextView = itemView.findViewById(R.id.taskName)
        private val statusButton: Button = itemView.findViewById(R.id.statusButton)

        fun bind(chore: Chore, position: Int) {
            taskName.text = chore.name
            setupStatusButton(statusButton, chore, onTaskClicked, position)
        }
        private fun setupStatusButton(button: Button, chore: Chore, onTaskClicked: (Chore, Int) -> Unit, position: Int) {

            button.apply {
                setOnClickListener(null)
                isEnabled = !(chore.locked || chore.completed)
                text = when {
                    chore.locked -> "Locked"
                    chore.completed -> "Completed"
                    else -> "Complete"
                }
                if (!chore.locked && !chore.completed) {
                    setOnClickListener { onTaskClicked(chore, position) }
                }
            }
        }
    }
}