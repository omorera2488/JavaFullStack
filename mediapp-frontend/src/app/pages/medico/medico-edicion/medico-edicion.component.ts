import { MedicoService } from './../../../_service/medico.service';
import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Medico } from 'src/app/_model/medico';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-medico-edicion',
  templateUrl: './medico-edicion.component.html',
  styleUrls: ['./medico-edicion.component.css']
})
export class MedicoEdicionComponent implements OnInit {

  medico: Medico;
  constructor(
    private dialogRef: MatDialogRef<MedicoEdicionComponent>,
    @Inject(MAT_DIALOG_DATA) private data: Medico,
    private medicoService:MedicoService
  ) { }

  ngOnInit() {
    this.medico = new Medico();
    this.medico.idMedico = this.data.idMedico;
    this.medico.nombres = this.data.nombres;
    this.medico.apellidos = this.data.apellidos;
    this.medico.cmp = this.data.cmp;
    this.medico.fotoUrl = this.data.fotoUrl;
  }

  cancelar() {
    this.dialogRef.close();
  }

  aceptar() {
    if (this.medico != null && this.medico.idMedico > 0) {
      //MODIFICAR
      //BUENA PRACTICA
      this.medicoService.modificar(this.medico).pipe(switchMap( () => {
        return  this.medicoService.listar();
      })).subscribe(data => {
        this.medicoService.medicoCambio.next(data);
        this.medicoService.mensajeCambio.next('Medico modificado');
      });
    }else{
      //REGISTRAR
      //PRACTICA COMUN
      this.medicoService.registrar(this.medico).subscribe(()=>{
        this.medicoService.listar().subscribe(data =>{
          this.medicoService.medicoCambio.next(data);
          this.medicoService.mensajeCambio.next('Medico registrado');
        });
      });
    }
    this.dialogRef.close();
  }

}
