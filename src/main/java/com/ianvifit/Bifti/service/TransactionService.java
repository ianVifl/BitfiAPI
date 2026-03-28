package com.ianvifit.Bifti.service;

import com.ianvifit.Bifti.model.Account;
import com.ianvifit.Bifti.model.Transaction;
import com.ianvifit.Bifti.model.TransactionType;
import com.ianvifit.Bifti.repository.AccountRepository;
import com.ianvifit.Bifti.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {


    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;


    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository= accountRepository;
    }


    @Transactional
    public Transaction retiro (Account cuenta, BigDecimal monto){
    if (cuenta.getSaldo().compareTo(monto) < 0 ){
        throw new IllegalArgumentException("NO hay suficiente saldo ");
    }else{
         cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
         accountRepository.save(cuenta);

         Transaction reciboTransaccion = new Transaction();
          reciboTransaccion.setAmount(monto); // El monto que pidieron retirar
          reciboTransaccion.setTipoDeTransaccion(TransactionType.valueOf("RETIRO"));// se pone valueOf ya que TransactionType es un enum y necesitamos convertir el texto "RETIRO" a su valor correspondiente en el enum TransactionType.RETIRO
        //fecha innecesaria ponerla ya que YA tenemos el JPA Auditing que se encarga de poner la fecha de transaccion automaticamente al momento de guardar el recibo en la base de datos
          reciboTransaccion.setAccount(cuenta); // Vinculamos la cuenta

        transactionRepository.save(reciboTransaccion); //guarda el "recibo" en DB/Repository
        return reciboTransaccion;// retorna el recibo de la transaccion para que se pueda mostrar al cliente o guardarlo en un historial
        }
    }

    @Transactional
    public Transaction deposito (Account cuenta, BigDecimal monto ){
    if(monto.compareTo(BigDecimal.ZERO) <= 0 ){
        throw new IllegalArgumentException("El monto a depositar no puede ser menor o igual a cero ");
    }else{
        cuenta.setSaldo(cuenta.getSaldo().add(monto));
        accountRepository.save(cuenta);

        Transaction reciboDeposito = new Transaction();
        reciboDeposito.setAmount(monto);
        reciboDeposito.setTipoDeTransaccion(TransactionType.valueOf("DEPOSITO"));
        reciboDeposito.setAccount(cuenta);

        transactionRepository.save(reciboDeposito);
        return reciboDeposito;
        }

    }
    @Transactional
    public List<Transaction> transferencia (Account cuentaOrigen, Account cuentaDestino, BigDecimal monto){
        if(cuentaDestino==null){
            throw new IllegalArgumentException("La cuenta a la que intentas transaferir no existe");
        }else{
            if(cuentaOrigen.getSaldo().compareTo(monto) >= 0 ) {
                Transaction reciboSalida = this.retiro(cuentaOrigen, monto);
                Transaction reciboEntrada= this.deposito(cuentaDestino, monto);
                return List.of(reciboSalida,reciboEntrada);
            }else{
                throw new IllegalArgumentException("NO hay suficiente dinero en tu cuenta");
            }

        }

    }
}
