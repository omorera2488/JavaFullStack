import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ExamenService } from 'src/app/_service/examen.service';
import { Examen } from 'src/app/_model/examen';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-examen-edicion',
  templateUrl: './examen-edicion.component.html',
  styleUrls: ['./examen-edicion.component.css']
})
export class ExamenEdicionComponent implements OnInit {
  
  form: FormGroup;
  examen:Examen;
  id: number; // id del examen en caso de edicion
  edicionFlag: boolean; // flag para activar modo edicion

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private examenService: ExamenService,
  ) { }

  ngOnInit() {
    this.examen = new Examen();

    this.form = new FormGroup({
      'id': new FormControl(0),
      'nombre': new FormControl(''),
      'descripcion': new FormControl(''),
    });


    this.route.params.subscribe((params: Params) => {
      this.id = params['id'];

      this.edicionFlag = params['id'] != null;
      this.initForm();
    });
  }

  initForm() {
    if (this.edicionFlag) {
      this.examenService.listarPorId(this.id).subscribe(data => {
        let id = data.idExamen;
        let nombre = data.nombre;
        let descripcion = data.descripcion

        this.form = new FormGroup({
          'id': new FormControl(id),
          'nombre': new FormControl(nombre),
          'descripcion': new FormControl(descripcion)
        });
      });
    }
  }

  submit() {
    this.examen.idExamen = this.form.value['id'];
    this.examen.nombre = this.form.value['nombre'];
    this.examen.descripcion = this.form.value['descripcion'];
    
    if (this.examen != null && this.examen.idExamen > 0) {
      this.examenService.modificar(this.examen).pipe(switchMap(() => {
        return this.examenService.listar();
      })).subscribe(data => {
        this.examenService.examenCambio.next(data);
        this.examenService.mensajeCambio.next("Examen modificado");
      });
    } else {
      this.examenService.registrar(this.examen).pipe(switchMap(() => {
        return this.examenService.listar();
      })).subscribe(data => {
        this.examenService.examenCambio.next(data);
        this.examenService.mensajeCambio.next("Examen registrado");
      });
    }

    this.router.navigate(['examen']);
  }

}
