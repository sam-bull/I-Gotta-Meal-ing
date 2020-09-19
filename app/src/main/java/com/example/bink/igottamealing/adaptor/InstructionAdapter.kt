package com.example.bink.igottamealing.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bink.igottamealing.databinding.ViewInstructionBinding

class InstructionAdapter (private val instructions: List<String>) :
    RecyclerView.Adapter<InstructionAdapter.InstructionViewHolder>() {

    class InstructionViewHolder(val binding: ViewInstructionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionViewHolder =
        InstructionViewHolder(
            ViewInstructionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = instructions.size

    override fun onBindViewHolder(holder: InstructionViewHolder, position: Int) {
        holder.binding.apply {
            instructionNumber = "${position + 1})"
            instruction = instructions[position]
            isLight = position % 2 == 0
        }
    }

}