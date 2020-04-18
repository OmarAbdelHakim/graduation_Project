package com.example.mybus3_4_2020.Fragement.Customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mybus3_4_2020.R;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mybus3_4_2020.Customer.Interface.ItemViewHolder222;
import com.example.mybus3_4_2020.Driver.DriverItems;
import com.example.mybus3_4_2020.Driver.model.Student;
import com.example.mybus3_4_2020.R;

import com.example.mybus3_4_2020.Customer.Interface.ItemClickListner;
import com.example.mybus3_4_2020.Customer.Interface.ItemViewHolder;


import android.animation.ObjectAnimator;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentFragment extends Fragment {


   private RecyclerView recyclerView;
   private List<Student> Itemssss = new ArrayList();
   private FirebaseRecyclerAdapter<Student , ItemViewHolder222> adapter2;
    private SparseBooleanArray expandestate = new SparseBooleanArray();

    public StudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_student, container, false);

        recyclerView = v.findViewById(R.id.rc_view3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RetrieveData_Student();

        setData();
        return v;
    }

    private void setData() {

        Query query2 = FirebaseDatabase.getInstance().getReference().child("Student");


        FirebaseRecyclerOptions<Student> options2 = new FirebaseRecyclerOptions.Builder<Student>()
                .setQuery(query2, Student.class)
                .build();

        adapter2 = new FirebaseRecyclerAdapter<Student, ItemViewHolder222>(options2) {

            @Override
            public int getItemViewType(int position) {

                if (Itemssss.get(position).isExxx())

                    return 1;

                else

                    return 0;


            }


            @Override
            protected void onBindViewHolder(@NonNull ItemViewHolder222 holder, final int position, @NonNull Student model) {

                switch (holder.getItemViewType()) {
                    case 0:// without item
                    {
                        ItemViewHolder222 viewHolder = (ItemViewHolder222) holder;

                        viewHolder.setIsRecyclable(false);

                        viewHolder. txt_Sub.setText(model.getSubject());

                        //event

                        viewHolder.setItemClickListner2(new ItemClickListner() {
                            @Override
                            public void onClick(View view, int position) {
                                Toast.makeText(getActivity(), "without child" + Itemssss.get(position).getSubject(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    break;

                    case 1: //with item


                    {
                        final ItemViewHolder222 viewHolder2 = (ItemViewHolder222) holder;

                        viewHolder2.setIsRecyclable(false);

                        viewHolder2. txt_Sub.setText(model.getSubject());


                        // because we use recycle View so we need to use expandble liner layout


                        ///////////////////////////////////////////////////////////////////////////////
                        //child 1 >> importance


                        ///////////////////////////////////////////////////////////////////////////


                        /////////////////////////////////////////////////////////////////////
                        // child 2 >> importance


                        viewHolder2.F_expandableLinearLayout.setInRecyclerView(true);
                        viewHolder2.F_expandableLinearLayout.setExpanded(expandestate.get(position));
                        viewHolder2.F_expandableLinearLayout.setListener(new ExpandableLayoutListenerAdapter() {
                            @Override
                            public void onPreOpen() {
                       /* ChangRotate(viewHolder.button , 0f , 180f).start();
                        expandestate.put(position, true);*/

                                ChangRotate_Student(viewHolder2.button2, 0f, 180).start();
                                expandestate.put(position, true);
                            }

                            @Override
                            public void onPreClose() {

                                ChangRotate_Student(viewHolder2.button2, 180f, 0f).start();
                                expandestate.put(position, false);


                            }
                        });

                        //second
                        viewHolder2.S_expandableLinearLayout.setInRecyclerView(true);
                        viewHolder2.S_expandableLinearLayout.setExpanded(expandestate.get(position));
                        viewHolder2.S_expandableLinearLayout.setListener(new ExpandableLayoutListenerAdapter() {
                            @Override
                            public void onPreOpen() {
                       /* ChangRotate(viewHolder.button , 0f , 180f).start();
                        expandestate.put(position, true);*/

                                ChangRotate_Student(viewHolder2.button2, 0f, 180).start();
                                expandestate.put(position, true);
                            }

                            @Override
                            public void onPreClose() {

                                ChangRotate_Student(viewHolder2.button2, 180f, 0f).start();
                                expandestate.put(position, false);


                            }
                        });

                        //THIRD

                        viewHolder2.T_expandableLinearLayout.setInRecyclerView(true);
                        viewHolder2.T_expandableLinearLayout.setExpanded(expandestate.get(position));
                        viewHolder2.T_expandableLinearLayout.setListener(new ExpandableLayoutListenerAdapter() {
                            @Override
                            public void onPreOpen() {
                       /* ChangRotate(viewHolder.button , 0f , 180f).start();
                        expandestate.put(position, true);*/

                                ChangRotate_Student(viewHolder2.button2, 0f, 180).start();
                                expandestate.put(position, true);
                            }

                            @Override
                            public void onPreClose() {

                                ChangRotate_Student(viewHolder2.button2, 180f, 0f).start();
                                expandestate.put(position, false);


                            }
                        });


                        viewHolder2.F4_expandableLinearLayout.setInRecyclerView(true);
                        viewHolder2.F4_expandableLinearLayout.setExpanded(expandestate.get(position));
                        viewHolder2.F4_expandableLinearLayout.setListener(new ExpandableLayoutListenerAdapter() {
                            @Override
                            public void onPreOpen() {
                       /* ChangRotate(viewHolder.button , 0f , 180f).start();
                        expandestate.put(position, true);*/

                                ChangRotate_Student(viewHolder2.button2, 0f, 180).start();
                                expandestate.put(position, true);
                            }

                            @Override
                            public void onPreClose() {

                                ChangRotate_Student(viewHolder2.button2, 180f, 0f).start();
                                expandestate.put(position, false);


                            }
                        });


                        viewHolder2.button2.setRotation(expandestate.get(position) ? 180f : 0f);
                        viewHolder2.button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                viewHolder2.F_expandableLinearLayout.toggle(); // child 1 >> importance
                                viewHolder2.S_expandableLinearLayout.toggle();
                                viewHolder2.T_expandableLinearLayout.toggle();
                                viewHolder2.F4_expandableLinearLayout.toggle();// child 2 >> importance
                            }
                        });
                        ///////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////


                        viewHolder2.txt_first.setText(model.getFirst());
                        viewHolder2.txt_first.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                viewHolder2.txt_first.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_account, 0, R.drawable.ic_login, 0);
                                // Toast.makeText(getActivity(), ""+viewHolder.txt_child.getText(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        viewHolder2.txt_second.setText(model.getSecond());
                        viewHolder2.txt_second.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                viewHolder2.txt_second.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_account, 0, R.drawable.ic_login, 0);;
                                // Toast.makeText(getActivity(), ""+viewHolder.txt_child.getText(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        viewHolder2.txt_third.setText(model.getThird());
                        viewHolder2.txt_third.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                viewHolder2.txt_third.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_account, 0, R.drawable.ic_login, 0);
                                // Toast.makeText(getActivity(), ""+viewHolder.txt_child.getText(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        viewHolder2.txt_fourth.setText(model.getFourth());
                        viewHolder2.txt_fourth.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                viewHolder2.txt_fourth.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_account, 0, R.drawable.ic_login, 0);

                                // Toast.makeText(getActivity(), ""+viewHolder.txt_child.getText(), Toast.LENGTH_SHORT).show();
                            }
                        });


/////////////////////////////////////////////////////////////////////////////////////////////


                        // secondChild
                        // viewHolder.txt_anotherchild.setText(model.getLine());
               /* viewHolder.txt_anotherchild.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(RecycleViewwithFirbase.this, ""+viewHolder.txt_anotherchild.getText(), Toast.LENGTH_SHORT).show();
                    }
                });*/


                        //set Event

                        viewHolder2.setItemClickListner2(new ItemClickListner() {
                            @Override
                            public void onClick(View view, int position) {


                                viewHolder2.F_expandableLinearLayout.toggle();
                                viewHolder2.S_expandableLinearLayout.toggle();
                                viewHolder2.T_expandableLinearLayout.toggle();
                                viewHolder2.F4_expandableLinearLayout.toggle();

                                // Toast.makeText(getActivity(), ""+model.getText(), Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                    break;
                    default:
                        break;
                }

            }





            @NonNull
            @Override
            public ItemViewHolder222 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if (viewType == 0) //without item

                // dy and 8ayrt feha
                {
                    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_without_child222, parent, false);
                    return new ItemViewHolder222(itemView, false); // false
                } else  // with item
                {
                    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_with_child222, parent, false);
                    return new ItemViewHolder222(itemView, true); // true

                }

            }




        };


        expandestate.clear();
        for (int i = 0; i < Itemssss.size(); i++)

            expandestate.append(i, false);



       // recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter2);

    }


        private ObjectAnimator ChangRotate_Student(RelativeLayout button2, float frommm, float tooo) {

            ObjectAnimator animator = ObjectAnimator.ofFloat(button2, "rotation", frommm, tooo);

            animator.setDuration(300);
            animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
            return animator;


        }


    private void RetrieveData_Student(){

        Itemssss.clear();
        DatabaseReference db = FirebaseDatabase.getInstance()
                .getReference()
                .child("Student");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot itemsnapshot : dataSnapshot.getChildren()) {
                    Student student = itemsnapshot.getValue(Student.class);


                    Itemssss.add(student);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.d("ERROR", "" + databaseError.getMessage());

            }
        });


    }

    @Override
    public void onStart() {

        if ( adapter2 !=null ) {

            adapter2.startListening();
        }


        super.onStart();
    }

    @Override
    public void onStop() {
        if (adapter2 !=null ) {

            adapter2.stopListening();
        }



        super.onStop();
    }



    }

