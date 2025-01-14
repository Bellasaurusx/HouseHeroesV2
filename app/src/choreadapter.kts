import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChoreAdapter(
    private val chores: List<Chore>,
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
            setupStatusButton(statusButton, chore, position)
        }
        private fun setupStatusButton(button: Button, chore: Chore, onTaskClicked: (Chore, Int) -> Unit, position: Int) {

            when {
                chore.locked -> {
                    statusButton.text = "Locked"
                    statusButton.isEnabled = false
                }
                chore.completed -> {
                    statusButton.text = "Completed"
                    statusButton.isEnabled = false
                }
                else -> {
                    statusButton.text = "Complete"
                    statusButton.isEnabled = true
                    statusButton.setOnClickListener {
                        onTaskClicked(chore, position)
                    }
                }
            }
        }
    }
}