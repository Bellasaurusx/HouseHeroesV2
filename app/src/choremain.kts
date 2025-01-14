import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class ChoreMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chore_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val chores = mutableListOf(
            Chore("Take out the trash", false, false),
            Chore("Clean your room", false, false),
            Chore("Do the dishes", true, false),
            Chore("Complete homework", true, false)
        )

        val adapter = ChoreAdapter(chores) { chore, position ->
            if (!chore.isActionable()) {
                showSnackbar(recyclerView, "This task is not actionable!")
            } else {
                chore.completed = true
                adapter.notifyItemChanged(position)
                showSnackbar(recyclerView, "Task completed: $(chore.name)")
            }
        }
        recyclerView.adapter = adapter
    }
    private fun showSnackbar(view: RecyclerView, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}

