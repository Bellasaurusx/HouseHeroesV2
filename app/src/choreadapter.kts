class ChoreAdapter(
    private val chores: List<Chore>,
    private val onComplete: (Chore) -> Unit
) : RecyclerView.Adapter<ChoreAdapter.ChoreViewHolder>() {
    inner class ChoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val choreName: TextView = itemView.findViewById(R.id.choreName)
        private val lockIcon: ImageView = itemView.findViewById(R.id.lockIcon)
        private val completeCheckBox: CheckBox = itemView.findViewById(R.id.completeCheckBox)

        fun bind(chore: Chore) {
            choreName.text = chore.name
            lockIcon.setImageResource(if (chore.isLocked) R.drawable.ic_locked else R.drawable.ic_unlocked)
            completeCheckBox.isEnabled = !chore.isLocked
            completeCheckBox.isChecked = chore.isCompleted

            completeCheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (!chore.isLocked) {
                    onComplete(chore.copy(isCompleted = isChecked))
                }
            }
        }
    }
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoreViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chore, parent, false)
    return ChoreViewHolder(view)
}

override fun onBindViewHolder(holder: ChoreViewHolder, position: Int) {
    holder.bind(chores[position])
}

override fun getItemCount() = chores.size
