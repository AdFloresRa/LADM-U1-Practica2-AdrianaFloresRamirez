package mx.tecnm.ladm_u1_practica2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//No pude comprobar si funciona como deberia, pero confio y espero en que si jale XD

        //GUARDAR
        btnGuardar.setOnClickListener {

            if (rbMI.isChecked){

                if(guardarEnMemoriaInterna()){
                    AlertDialog.Builder(this).setTitle("ATENCION")
                        .setMessage("SE PUDO GUARDAR EN DATA")
                        .setPositiveButton("ok") { d, i -> d.dismiss() }
                        .show()
                }else{
                    AlertDialog.Builder(this).setTitle("ERROR")
                        .setMessage("NO SE PUDO GUARDAR EN DATA")
                        .setPositiveButton("ok") { d, i -> d.dismiss() }
                        .show()
                }

            }else{

                if (guardarEnMemoriaSD()){
                    AlertDialog.Builder(this).setTitle("ATENCION")
                        .setMessage("SE PUDO GUARDAR EN SD")
                        .setPositiveButton("ok") { d, i -> d.dismiss() }
                        .show()
                }else{
                    AlertDialog.Builder(this).setTitle("ERROR")
                        .setMessage("NO SE PUDO GUARDAR EN SD")
                        .setPositiveButton("ok") { d, i -> d.dismiss() }
                        .show()
                }

            }

        }

        //LEER
        btnAbrir.setOnClickListener {

            if (rbMI.isChecked){

                if(leerMemoriaInterna()){
                    AlertDialog.Builder(this).setTitle("ATENCION")
                        .setMessage("SE LEYO DATA")
                        .setPositiveButton("ok"){d,i->d.dismiss()}
                        .show()
                }else{
                    AlertDialog.Builder(this).setTitle("ERROR")
                        .setMessage("NO SE PUDO LEER/ENCONTRAR DATA")
                        .setPositiveButton("ok"){d,i->d.dismiss()}
                        .show()
                }

            }else{

                if(leerMemoriaSD()){
                    AlertDialog.Builder(this).setTitle("ATENCION")
                        .setMessage("SE LEYO SD")
                        .setPositiveButton("ok"){d,i->d.dismiss()}
                        .show()
                }else{
                    AlertDialog.Builder(this).setTitle("ERROR")
                        .setMessage("NO SE PUDO LEER/ENCONTRAR SD")
                        .setPositiveButton("ok"){d,i->d.dismiss()}
                        .show()
                }

            }

        }

    }

//FUNCIONES
    private fun guardarEnMemoriaInterna() : Boolean{

        try {

            var nombreArchivo = txtNombreArchivo.text.toString()
            var flujoSalida = OutputStreamWriter(openFileOutput(nombreArchivo, Context.MODE_PRIVATE))
            var datos = Frase.text.toString()

            flujoSalida.write(datos)
            flujoSalida.flush()
            flujoSalida.close()

        }catch (ioE : IOException){

            return false;
        }

        return true;

    }

    private fun leerMemoriaInterna () : Boolean {

        try {

            var nombreArchivo = txtNombreArchivo.text.toString()
            var flujoEntrada = BufferedReader(InputStreamReader(openFileInput(nombreArchivo)))
            var datos = flujoEntrada.readLine()
            var x = StringBuilder()

            while (datos != null){
                x.append(datos + "\n")
                datos = flujoEntrada.readLine()
            }

            Frase.setText(x.toString())
            flujoEntrada.close()


        }catch (ioE: IOException){

            return false;
        }

        return true;

    }


    private fun guardarEnMemoriaSD () : Boolean{

        try {

            var nombreArchivoSD = txtNombreArchivo.text.toString()
            var rutaSD = Environment.getExternalStorageDirectory()
            var datosArchivo = File(rutaSD.absolutePath, nombreArchivoSD)
            var flujoSalida = OutputStreamWriter(FileOutputStream(datosArchivo))
            var datos = Frase.text.toString()

            flujoSalida.write(datos)
            flujoSalida.flush()
            flujoSalida.close()

        }catch (ioE: IOException){

            return false;
        }

        return true;

    }


    private fun leerMemoriaSD () : Boolean{

        try {

            var nombreArchivo = txtNombreArchivo.text.toString()
            var rutaSD = Environment.getExternalStorageDirectory()
            var datosArchivos = File(rutaSD.absolutePath, nombreArchivo)
            var flujoEntrada = BufferedReader(InputStreamReader(FileInputStream(datosArchivos)))

            var datos = flujoEntrada.readLine()
            var x = StringBuilder()

            while (datos != null){
                x.append(datos+"\n")
                datos = flujoEntrada.readLine()
            }

            Frase.setText(x)
            flujoEntrada.close()

        }catch (ioE: IOException){

            return false;
        }

        return true;
    }

}

}