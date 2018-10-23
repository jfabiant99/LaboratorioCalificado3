package pe.edu.tecsup.jfabian.laboratoriocalificado3.adaptador;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.orm.SugarRecord;

import java.util.List;

import pe.edu.tecsup.jfabian.laboratoriocalificado3.R;
import pe.edu.tecsup.jfabian.laboratoriocalificado3.modelo.Nota;
import pe.edu.tecsup.jfabian.laboratoriocalificado3.repositorio.RepositorioNota;
import pe.edu.tecsup.jfabian.laboratoriocalificado3.vistas.Detalle;

public class NotaAdaptador extends RecyclerView.Adapter<NotaAdaptador.ViewHolder> {
    private static final String TAG = NotaAdaptador.class.getSimpleName();
    private List<Nota> notas;

    public NotaAdaptador(List<Nota> notas) {
        this.notas = notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
    @NonNull
    @Override
    public NotaAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notas,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NotaAdaptador.ViewHolder holder, final int position) {
        final Nota note = this.notas.get(position);
        int color = ColorGenerator.MATERIAL.getColor(note.getTitulo());
        TextDrawable drawable = TextDrawable.builder().buildRect(note.getTitulo().substring(0,1),color);
        holder.pictureImage.setImageDrawable(drawable);
        holder.titulo.setText(note.getTitulo());
        holder.descri.setText(note.getDescripcion());
        holder.fecha.setReferenceTime(note.getFecha().getTime());
        /*
            OJO CON ESTO PARA PREGUNTAR AL PROFE DE MRD
        holder.archivar2.setChecked(note.getArchivar());*/

        holder.favorito.setChecked(note.getFavorito());


        holder.favorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                note.setFavorito(b);
//                updateFavorito(b, note.getId());
                note.setFavorito(b);
                SugarRecord.save(note);
            }
        });

        holder.archivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RepositorioNota.eliminar(note.getId());
                notas.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,getItemCount());
                Toast.makeText(view.getContext(),"Nota Archivada correctamente",Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Detalle.class);
                intent.putExtra("ID",note.getId());
                view.getContext().startActivity(intent);
//                if (holder.favorito.isChecked()){
////                    updateFavorito(true,note.getId());
//                    Toast.makeText(view.getContext(),"FAVORITO",Toast.LENGTH_SHORT).show();
//                }
//                if(holder.archivar2.isChecked()){
//                    Toast.makeText(view.getContext(),"archivado",Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return  notas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo;
        public TextView descri;
        public RelativeTimeTextView fecha;
        public ImageView pictureImage;
        public ImageButton archivar;
        public CheckBox favorito;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView)itemView.findViewById(R.id.notas_titulo);
            descri = (TextView)itemView.findViewById(R.id.notas_descripcion);
            fecha = (RelativeTimeTextView)itemView.findViewById(R.id.notas_fecha);
            pictureImage = (ImageView)itemView.findViewById(R.id.picture_image);
            //Eliminar es el boton archivar

            archivar = (ImageButton)itemView.findViewById(R.id.archivar);
            favorito = (CheckBox)itemView.findViewById(R.id.favorito);

        }
    }
    public void updateFavorito(Boolean favorito,Long id){
        Nota nota = SugarRecord.findById(Nota.class,id);
        nota.setFavorito(true);
        SugarRecord.save(nota);
    }
}