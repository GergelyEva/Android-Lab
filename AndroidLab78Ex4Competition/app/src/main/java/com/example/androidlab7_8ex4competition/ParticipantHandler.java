package com.example.androidlab7_8ex4competition;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ParticipantHandler extends AppCompatActivity {

    private ArrayList<Participant> participants;
    private EditText firstNameEditText, indexEditText, scoreEditText, deleteIndexEditText,
            modifyScoreEditText, modifyFirstNameEditText, modifyIndexEditText, scoreComparison, nameStartText;
    private TextView participantListTextView;
    private int index;

    private View layoutmoddel, layoutsort;
    private Button addButton, sortByNameButton, sortByScoreButton, sortByNameButtonDesc, sortByScoreButtonDesc,
            deleteParticipantButton, modifyParticipantButton, scoreBigger, scoreLesser, nameStarts, sortButton, modifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_handler);

        participants = new ArrayList<>();

        indexEditText = findViewById(R.id.indexEditText);

        firstNameEditText = findViewById(R.id.fn);
        scoreEditText = findViewById(R.id.score);
        participantListTextView = findViewById(R.id.plist);
        addButton = findViewById(R.id.addbttn);
        sortByNameButton = findViewById(R.id.sortname);
        sortByScoreButton = findViewById(R.id.sortgrade);
        sortByNameButtonDesc = findViewById(R.id.sortnamedesc);
        sortByScoreButtonDesc = findViewById(R.id.sortgradedesc);
        deleteParticipantButton = findViewById(R.id.deletebtn);
        modifyParticipantButton = findViewById(R.id.modifybtn);
        modifyFirstNameEditText = findViewById(R.id.modifyFirstNameEditText);
        modifyScoreEditText = findViewById(R.id.modifyScoreEditText);
        modifyIndexEditText = findViewById(R.id.modifyIndexEditText);
        scoreBigger = findViewById(R.id.scbigger);
        scoreLesser = findViewById(R.id.sclesser);
        scoreComparison = findViewById(R.id.scorecomparisontext);
        nameStarts = findViewById(R.id.nstart);
        nameStartText = findViewById(R.id.nstarttext);
        layoutmoddel=findViewById(R.id.modifydeletelayout);
        layoutsort=findViewById(R.id.sortlayout);
        sortButton=findViewById(R.id.sortbttn);
        modifyButton=findViewById(R.id.modifydeletebttn);

        layoutmoddel.setVisibility(View.GONE);
        layoutsort.setVisibility(View.GONE);

        sortButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (layoutsort.getVisibility() == View.GONE) {
                layoutsort.setVisibility(View.VISIBLE);
            } else if (layoutsort.getVisibility() == View.VISIBLE) {
                layoutsort.setVisibility(View.GONE);
            }
        }
        });

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(layoutmoddel.getVisibility()==View.GONE){
                    layoutmoddel.setVisibility(View.VISIBLE);
                }
                else if (layoutmoddel.getVisibility()==View.VISIBLE){
                    layoutmoddel.setVisibility(View.GONE);
                }
            }

        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input

                String firstName = firstNameEditText.getText().toString();
                int score = Integer.parseInt(scoreEditText.getText().toString());

                // Calculate the index (assuming 0-based index)
                int index = Integer.parseInt(indexEditText.getText().toString());
                participants.add(new Participant(index, firstName, score));

                // Add new participant to the list with auto-assigned index

                // Update participant list view
                updateParticipantList();
                firstNameEditText.setText("");
                scoreEditText.setText("");
            }
        });


        sortByNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortParticipantsByName();
            }
        });

        sortByScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortParticipantsByScore();
            }
        });

        sortByNameButtonDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortParticipantsByNameDesc();
            }
        });

        sortByScoreButtonDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortParticipantsByScoreDesc();
            }
        });


        scoreBigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreBiggerThan();
            }
        });
        scoreLesser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreLessThan();
            }
        });

        nameStarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByNameStart();
            }
        });


        modifyParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indexToModify = Integer.parseInt(modifyIndexEditText.getText().toString());
                String newFirstName = modifyFirstNameEditText.getText().toString();
                int newScore = Integer.parseInt(modifyScoreEditText.getText().toString());

                for (int i = 0; i < participants.size(); i++) {
                    Participant participant = participants.get(i);
                    if (participant.getIndex() == indexToModify) {
                        participant.setName(newFirstName);
                        participant.setScore(newScore);
                        updateParticipantList();
                        return; // Exit the loop after modification
                    }
                }
                // Show a message if participant with the index is not found
                Toast.makeText(ParticipantHandler.this, "Participant not found!", Toast.LENGTH_SHORT).show();
            }
        });

        deleteParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indexToDelete = Integer.parseInt(deleteIndexEditText.getText().toString());
                ArrayList<Participant> participantsToDelete = new ArrayList<>(participants); // Create a copy

                for (int i = participantsToDelete.size() - 1; i >= 0; i--) { // Iterate from back to avoid index issues
                    Participant participant = participantsToDelete.get(i);
                    if (participant.getIndex() == indexToDelete) {
                        participants.remove(participant); // Remove from original list
                        updateParticipantList();
                        return; // Exit the loop after deletion
                    }
                }
                // Show a message if participant with the index is not found
                Toast.makeText(ParticipantHandler.this, "Participant not found!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateParticipantList() {
        StringBuilder builder = new StringBuilder();
        for (Participant participant : participants) {
            builder.append(participant.getIndex() + "-" + participant.getName() + " - " + participant.getScore() + "\n");
        }
        participantListTextView.setText(builder.toString());
    }

    private void sortParticipantsByName() {
        Collections.sort(participants, new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                return p1.getName().compareToIgnoreCase(p2.getName());
            }
        });
        updateParticipantList();
    }

    private void sortParticipantsByScore() {
        Collections.sort(participants, new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                return Integer.compare(p1.getScore(), p2.getScore());
            }
        });
        updateParticipantList();

    }

    private void scoreBiggerThan() {
        // Get the comparison score from the input field
        int score = Integer.parseInt(scoreComparison.getText().toString());

        // Filter participants with scores greater than the comparison score
        List<Participant> filteredParticipants = new ArrayList<>();
        for (Participant participant : participants) {
            if (participant.getScore() > score) {
                filteredParticipants.add(participant);
            }
        }

        // Sort the filtered participants
        Collections.sort(filteredParticipants, new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                return Integer.compare(p1.getScore(), p2.getScore());
            }
        });

        // Update the participant list with the sorted and filtered participants
        participants.clear();
        participants.addAll(filteredParticipants);
        updateParticipantList();
    }

    private void scoreLessThan() {
        // Get the comparison score from the input field
        int score = Integer.parseInt(scoreComparison.getText().toString());

        // Filter participants with scores greater than the comparison score
        List<Participant> filteredParticipants = new ArrayList<>();
        for (Participant participant : participants) {
            if (participant.getScore() < score) {
                filteredParticipants.add(participant);
            }
        }

        // Sort the filtered participants
        Collections.sort(filteredParticipants, new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                return Integer.compare(p1.getScore(), p2.getScore());
            }
        });

        // Update the participant list with the sorted and filtered participants
        participants.clear();
        participants.addAll(filteredParticipants);
        updateParticipantList();
    }


    private void sortParticipantsByNameDesc() {
        Collections.sort(participants, new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                // Compare names in descending order
                return p2.getName().compareToIgnoreCase(p1.getName());
            }
        });
        updateParticipantList();
    }

    private void sortParticipantsByScoreDesc() {
        Collections.sort(participants, new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                // Compare scores in descending order
                return Integer.compare(p2.getScore(), p1.getScore());
            }
        });
        updateParticipantList();
    }

    private void sortByNameStart() {
        // Get the comparison score from the input field
        String nameStartSort=nameStartText.getText().toString();
        // Filter participants with names starting with the specified string
        List<Participant> filteredParticipants = new ArrayList<>();
        for (Participant participant : participants) {
            if (participant.getName().startsWith(nameStartSort)) {
                filteredParticipants.add(participant);
            }
        }

        // Sort the filtered participants alphabetically by name in ascending order
        Collections.sort(filteredParticipants, new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                return p1.getName().compareToIgnoreCase(p2.getName());
            }
        });

        // Update the participant list with the sorted and filtered participants
        participants.clear();
        participants.addAll(filteredParticipants);
        updateParticipantList();
    }
}
