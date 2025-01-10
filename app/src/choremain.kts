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
            Chore("Take out the trash", false)
            Chore("Clean your room", false)
            Chore("Do the dishes", true)
            Chore("Complete homework", true)
        )

        val adapter = ChoreAdapter(chores) { chore, position ->
            if (chore.locked) {
                Snackbar.make(recycleView, "This task is locked!", Snackbar.LENGTH_SHORT).
            } else {
                chore.completed = true
                adapter.notifyItemChanged(position)
            }
        }
        recyclerView.adapter = adapter
    }
}

data class Chore(val name: String, var locked: Boolean, var completed: Boolean = false)