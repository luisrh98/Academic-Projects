package com.example.aplicacion1.ui.qr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacion1.R;
import com.example.aplicacion1.data.model.Producto;
import com.example.aplicacion1.data.repository.PersistenciaUtils;
import com.example.aplicacion1.ui.carrito.CarritoActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrScannerActivity extends AppCompatActivity {

    private CarritoActivity carritoActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);

        // Iniciar el escáner de códigos QR
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                // El usuario canceló el escaneo
                Toast.makeText(this, "Escaneo cancelado", Toast.LENGTH_SHORT).show();
                finish(); // Volver a la actividad anterior
            } else {
                // Procesar el contenido del código QR
                String qrContent = result.getContents();
                procesarCodigoQR(qrContent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void procesarCodigoQR(String qrContent) {
        // Verificar si el código QR contiene un descuento válido
        if (esCodigoDescuentoValido(qrContent)) {
            // Aplicar el descuento al carrito
            double descuento = obtenerDescuentoDesdeCodigo(qrContent);
            aplicarDescuentoAlCarrito(descuento);
            Toast.makeText(this, "Descuento aplicado: " + descuento + "%", Toast.LENGTH_LONG).show();
        } else {
            // Mostrar un mensaje si el código QR no es válido
            Toast.makeText(this, "Código QR no válido", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean esCodigoDescuentoValido(String qrContent) {
        // Ejemplo: Los códigos QR válidos comienzan con "DESCUENTO_"
        return qrContent.startsWith("DESCUENTO_");
    }

    private double obtenerDescuentoDesdeCodigo(String qrContent) {
        // Extraer el porcentaje de descuento del código QR
        String descuentoStr = qrContent.replace("DESCUENTO_", "");
        try {
            return Double.parseDouble(descuentoStr);
        } catch (NumberFormatException e) {
            return 0; // Si el formato no es válido, no aplicar descuento
        }
    }

    private void aplicarDescuentoAlCarrito(double descuento) {
        // Calcular el total actual del carrito
        double totalActual = calcularTotalCarrito();

        // Calcular el nuevo total con el descuento aplicado
        double descuentoAplicado = totalActual * (descuento / 100);
        double nuevoTotal = totalActual - descuentoAplicado;

        // Guardar el nuevo total en SharedPreferences o actualizar la interfaz
        PersistenciaUtils.guardarTotalCarrito(this, nuevoTotal);

        // Mostrar el nuevo total en la interfaz (opcional)
        Toast.makeText(this, "Nuevo total: $" + String.format("%.2f", nuevoTotal), Toast.LENGTH_LONG).show();
    }

    private double calcularTotalCarrito() {
        // Obtener los productos del carrito y calcular el total
        double total = 0;
        for (Producto producto : CarritoActivity.getCarritoProductos()) {
            total += producto.getPrecio();
        }
        return total;
    }
} // <--- Asegúrate de que esta llave esté presente