"use client"

import { useState } from "react"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import {
  Film,
  Users,
  Database,
  Code,
  Play,
  ChevronRight,
  Monitor,
  UserCheck,
  Calendar,
  ShoppingCart,
  Settings,
} from "lucide-react"

export default function CinemaParadisoLanding() {
  const [activeTab, setActiveTab] = useState("overview")

  const features = [
    {
      icon: <Users className="h-6 w-6" />,
      title: "Gestión de Usuarios",
      description: "Sistema completo CRUD para clientes y empleados con roles diferenciados",
    },
    {
      icon: <Film className="h-6 w-6" />,
      title: "Administración de Películas",
      description: "Control total de cartelera, salas, horarios y funciones",
    },
    {
      icon: <ShoppingCart className="h-6 w-6" />,
      title: "Sistema de Ventas",
      description: "Gestión de tickets, reservas y venta de alimentos integrada",
    },
    {
      icon: <Database className="h-6 w-6" />,
      title: "Modelo CRUD Completo",
      description: "Operaciones Crear, Leer, Actualizar y Eliminar para todas las entidades",
    },
  ]

  const useCases = [
    {
      actor: "Administrador",
      cases: [
        "Gestionar Películas",
        "Gestionar Funciones",
        "Gestionar Salas",
        "Gestionar Empleados",
        "Gestionar Turnos",
      ],
    },
    {
      actor: "Empleado",
      cases: ["Atender Reservas", "Registrar Venta de Comida", "Validar Tickets"],
    },
    {
      actor: "Cliente",
      cases: ["Comprar Ticket", "Reservar Función", "Registrarse", "Iniciar Sesión", "Ver Cartelera", "Comprar Comida"],
    },
  ]

  const entities = [
    {
      name: "Movie",
      attributes: [
        "idMovie: int",
        "title: string",
        "description: string",
        "gender: string",
        "duration: int",
        "imgUrl: string",
      ],
    },
    { name: "Customer", attributes: ["idCustomer: int", "name: string", "email: string", "password: string"] },
    { name: "Room", attributes: ["idRoom: int", "roomNumber: int", "capacity: int"] },
    { name: "Employee", attributes: ["idEmployee: int", "name: string", "position: string"] },
    { name: "Screening", attributes: ["idScreening: int", "dateTime: date"] },
    { name: "Ticket", attributes: ["idTicket: int", "price: decimal"] },
    { name: "Reservation", attributes: ["idReservation: int", "ticketQuantity: int"] },
    { name: "Food", attributes: ["idFood: int", "name: string", "price: decimal", "imgUrl: string"] },
  ]

  return (
    <div className="min-h-screen bg-background">
      {/* Header */}
      <header className="sticky top-0 z-50 w-full border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60">
        <div className="container flex h-16 items-center justify-between">
          <div className="flex items-center space-x-2">
            <Film className="h-8 w-8 text-primary" />
            <span className="text-xl font-bold">Cinema Paradiso</span>
          </div>
          <nav className="hidden md:flex items-center space-x-6">
            <a href="#overview" className="text-sm font-medium hover:text-primary transition-colors">
              Resumen
            </a>
            <a href="#use-cases" className="text-sm font-medium hover:text-primary transition-colors">
              Casos de Uso
            </a>
            <a href="#diagrams" className="text-sm font-medium hover:text-primary transition-colors">
              Diagramas
            </a>
            <a href="#architecture" className="text-sm font-medium hover:text-primary transition-colors">
              Arquitectura
            </a>
          </nav>
        </div>
      </header>

      {/* Hero Section */}
      <section className="py-20 px-4">
        <div className="container mx-auto text-center">
          <Badge variant="secondary" className="mb-4">
            Sistema de Gestión Cinematográfica
          </Badge>
          <h1 className="text-4xl md:text-6xl font-bold mb-6 text-balance">
            <span className="text-primary">Cinema Paradiso</span>
            <br />
            Sistema Integral de Gestión
          </h1>
          <p className="text-xl text-muted-foreground mb-8 max-w-3xl mx-auto text-pretty">
            Sistema de gestión para un cine que permite administrar de manera centralizada todos los procesos
            relacionados con la operación diaria. Implementado bajo el modelo CRUD para cada entidad principal.
          </p>
          <div className="flex flex-col sm:flex-row gap-4 justify-center">
            <Button size="lg" className="bg-primary hover:bg-primary/90">
              Explorar Documentación
              <ChevronRight className="h-4 w-4 ml-2" />
            </Button>
            <Button size="lg" variant="outline">
              <Code className="h-4 w-4 mr-2" />
              Ver Diagramas UML
            </Button>
          </div>
        </div>
      </section>

      {/* Features Grid */}
      <section className="py-16 px-4 bg-muted/50">
        <div className="container mx-auto">
          <h2 className="text-3xl font-bold text-center mb-12">Características del Sistema</h2>
          <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-6">
            {features.map((feature, index) => (
              <Card key={index} className="hover:shadow-lg transition-shadow">
                <CardHeader>
                  <div className="h-12 w-12 bg-primary/10 rounded-lg flex items-center justify-center text-primary mb-4">
                    {feature.icon}
                  </div>
                  <CardTitle className="text-lg">{feature.title}</CardTitle>
                </CardHeader>
                <CardContent>
                  <CardDescription>{feature.description}</CardDescription>
                </CardContent>
              </Card>
            ))}
          </div>
        </div>
      </section>

      {/* Technical Documentation */}
      <section className="py-16 px-4">
        <div className="container mx-auto">
          <h2 className="text-3xl font-bold text-center mb-12">Documentación Técnica</h2>

          <Tabs value={activeTab} onValueChange={setActiveTab} className="w-full">
            <TabsList className="grid w-full grid-cols-5">
              <TabsTrigger value="overview">Resumen</TabsTrigger>
              <TabsTrigger value="use-cases">Casos de Uso</TabsTrigger>
              <TabsTrigger value="class-diagram">Diagrama de Clases</TabsTrigger>
              <TabsTrigger value="sequence">Secuencia</TabsTrigger>
              <TabsTrigger value="architecture">Arquitectura</TabsTrigger>
            </TabsList>

            <TabsContent value="overview" className="mt-8">
              <div className="grid md:grid-cols-2 gap-8">
                <Card>
                  <CardHeader>
                    <CardTitle className="flex items-center gap-2">
                      <Settings className="h-5 w-5" />
                      Modelo CRUD
                    </CardTitle>
                  </CardHeader>
                  <CardContent>
                    <div className="space-y-4">
                      <div className="flex items-center gap-3">
                        <div className="h-2 w-2 bg-primary rounded-full"></div>
                        <span>
                          <strong>Create:</strong> Crear nuevos registros
                        </span>
                      </div>
                      <div className="flex items-center gap-3">
                        <div className="h-2 w-2 bg-primary rounded-full"></div>
                        <span>
                          <strong>Read:</strong> Consultar información
                        </span>
                      </div>
                      <div className="flex items-center gap-3">
                        <div className="h-2 w-2 bg-primary rounded-full"></div>
                        <span>
                          <strong>Update:</strong> Actualizar datos existentes
                        </span>
                      </div>
                      <div className="flex items-center gap-3">
                        <div className="h-2 w-2 bg-primary rounded-full"></div>
                        <span>
                          <strong>Delete:</strong> Eliminar registros
                        </span>
                      </div>
                    </div>
                  </CardContent>
                </Card>

                <Card>
                  <CardHeader>
                    <CardTitle>Entidades Principales</CardTitle>
                  </CardHeader>
                  <CardContent>
                    <div className="grid grid-cols-2 gap-3">
                      <div className="p-3 bg-muted rounded-lg text-center">
                        <Film className="h-5 w-5 mx-auto mb-1 text-primary" />
                        <div className="text-sm font-medium">Movie</div>
                      </div>
                      <div className="p-3 bg-muted rounded-lg text-center">
                        <Users className="h-5 w-5 mx-auto mb-1 text-primary" />
                        <div className="text-sm font-medium">Customer</div>
                      </div>
                      <div className="p-3 bg-muted rounded-lg text-center">
                        <Monitor className="h-5 w-5 mx-auto mb-1 text-primary" />
                        <div className="text-sm font-medium">Room</div>
                      </div>
                      <div className="p-3 bg-muted rounded-lg text-center">
                        <UserCheck className="h-5 w-5 mx-auto mb-1 text-primary" />
                        <div className="text-sm font-medium">Employee</div>
                      </div>
                      <div className="p-3 bg-muted rounded-lg text-center">
                        <Calendar className="h-5 w-5 mx-auto mb-1 text-primary" />
                        <div className="text-sm font-medium">Screening</div>
                      </div>
                      <div className="p-3 bg-muted rounded-lg text-center">
                        <ShoppingCart className="h-5 w-5 mx-auto mb-1 text-primary" />
                        <div className="text-sm font-medium">Ticket</div>
                      </div>
                    </div>
                  </CardContent>
                </Card>
              </div>
            </TabsContent>

            <TabsContent value="use-cases" className="mt-8">
              <Card>
                <CardHeader>
                  <CardTitle>Diagrama de Casos de Uso</CardTitle>
                  <CardDescription>Funcionalidades por tipo de usuario en Cinema Paradiso</CardDescription>
                </CardHeader>
                <CardContent>
                  <div className="mb-8">
                    <img
                      src="https://hebbkx1anhila5yf.public.blob.vercel-storage.com/use_case-QO8vFYiCYf7bSiqumDXfJdDIdPXFLQ.png"
                      alt="Diagrama de Casos de Uso - Cinema Paradiso"
                      className="w-full max-w-4xl mx-auto rounded-lg border"
                    />
                  </div>

                  <div className="grid md:grid-cols-3 gap-6">
                    {useCases.map((actor, index) => (
                      <div key={index} className="bg-muted p-6 rounded-lg">
                        <h4 className="font-bold text-primary mb-4 flex items-center gap-2">
                          <Users className="h-5 w-5" />
                          {actor.actor}
                        </h4>
                        <div className="space-y-2">
                          {actor.cases.map((useCase, caseIndex) => (
                            <div key={caseIndex} className="flex items-center gap-2 text-sm">
                              <div className="h-1.5 w-1.5 bg-primary rounded-full"></div>
                              {useCase}
                            </div>
                          ))}
                        </div>
                      </div>
                    ))}
                  </div>
                </CardContent>
              </Card>
            </TabsContent>

            <TabsContent value="class-diagram" className="mt-8">
              <Card>
                <CardHeader>
                  <CardTitle>Diagrama de Clases UML</CardTitle>
                  <CardDescription>Modelo de datos completo con operaciones CRUD para cada entidad</CardDescription>
                </CardHeader>
                <CardContent>
                  <div className="mb-8">
                    <img
                      src="https://hebbkx1anhila5yf.public.blob.vercel-storage.com/class-eQ7Qfwe7UJNYE9HjeW24s1UvoBjnzS.png"
                      alt="Diagrama de Clases UML - Cinema Paradiso"
                      className="w-full max-w-6xl mx-auto rounded-lg border"
                    />
                  </div>

                  <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
                    {entities.map((entity, index) => (
                      <div key={index} className="bg-background border rounded-lg p-4">
                        <h4 className="font-bold text-primary mb-3 flex items-center gap-2">
                          <Database className="h-4 w-4" />
                          {entity.name}
                        </h4>
                        <div className="text-sm space-y-1 mb-3">
                          {entity.attributes.map((attr, attrIndex) => (
                            <div key={attrIndex} className="text-muted-foreground">
                              + {attr}
                            </div>
                          ))}
                        </div>
                        <hr className="my-2" />
                        <div className="text-sm space-y-1">
                          <div className="text-accent">+ create()</div>
                          <div className="text-accent">+ read()</div>
                          <div className="text-accent">+ update()</div>
                          <div className="text-accent">+ delete()</div>
                        </div>
                      </div>
                    ))}
                  </div>
                </CardContent>
              </Card>
            </TabsContent>

            <TabsContent value="sequence" className="mt-8">
              <Card>
                <CardHeader>
                  <CardTitle>Diagrama de Secuencia</CardTitle>
                  <CardDescription>Flujo de compra de tickets paso a paso</CardDescription>
                </CardHeader>
                <CardContent>
                  <div className="mb-8">
                    <img
                      src="https://hebbkx1anhila5yf.public.blob.vercel-storage.com/secuence-B1oVK5VdvdNT5sMEKQLns0jA4nA7FK.png"
                      alt="Diagrama de Secuencia - Compra de Tickets"
                      className="w-full max-w-5xl mx-auto rounded-lg border"
                    />
                  </div>

                  <div className="bg-muted p-6 rounded-lg">
                    <h4 className="font-bold mb-4">Flujo de Compra de Tickets</h4>
                    <div className="space-y-4">
                      <div className="flex items-center gap-4">
                        <div className="w-16 h-8 bg-primary/20 rounded flex items-center justify-center text-xs font-medium">
                          Cliente
                        </div>
                        <ChevronRight className="h-4 w-4 text-primary" />
                        <div className="w-16 h-8 bg-secondary/20 rounded flex items-center justify-center text-xs font-medium">
                          Sistema
                        </div>
                        <div className="flex-1 text-sm bg-background p-2 rounded border">1. Iniciar Sesión</div>
                      </div>

                      <div className="flex items-center gap-4">
                        <div className="w-16 h-8 bg-primary/20 rounded flex items-center justify-center text-xs font-medium">
                          Cliente
                        </div>
                        <ChevronRight className="h-4 w-4 text-primary" />
                        <div className="w-16 h-8 bg-secondary/20 rounded flex items-center justify-center text-xs font-medium">
                          Sistema
                        </div>
                        <div className="flex-1 text-sm bg-background p-2 rounded border">2. Ver Cartelera</div>
                      </div>

                      <div className="flex items-center gap-4">
                        <div className="w-16 h-8 bg-secondary/20 rounded flex items-center justify-center text-xs font-medium">
                          Sistema
                        </div>
                        <ChevronRight className="h-4 w-4 text-accent" />
                        <div className="w-16 h-8 bg-accent/20 rounded flex items-center justify-center text-xs font-medium">
                          Movie
                        </div>
                        <div className="flex-1 text-sm bg-background p-2 rounded border">3. Consultar películas</div>
                      </div>

                      <div className="flex items-center gap-4">
                        <div className="w-16 h-8 bg-primary/20 rounded flex items-center justify-center text-xs font-medium">
                          Cliente
                        </div>
                        <ChevronRight className="h-4 w-4 text-primary" />
                        <div className="w-16 h-8 bg-secondary/20 rounded flex items-center justify-center text-xs font-medium">
                          Sistema
                        </div>
                        <div className="flex-1 text-sm bg-background p-2 rounded border">4. Seleccionar Función</div>
                      </div>

                      <div className="flex items-center gap-4">
                        <div className="w-16 h-8 bg-secondary/20 rounded flex items-center justify-center text-xs font-medium">
                          Sistema
                        </div>
                        <ChevronRight className="h-4 w-4 text-accent" />
                        <div className="w-16 h-8 bg-accent/20 rounded flex items-center justify-center text-xs font-medium">
                          Ticket
                        </div>
                        <div className="flex-1 text-sm bg-background p-2 rounded border">5. Generar ticket</div>
                      </div>
                    </div>
                  </div>
                </CardContent>
              </Card>
            </TabsContent>

            <TabsContent value="architecture" className="mt-8">
              <Card>
                <CardHeader>
                  <CardTitle>Arquitectura por Capas</CardTitle>
                  <CardDescription>Separación clara de responsabilidades en módulos especializados</CardDescription>
                </CardHeader>
                <CardContent>
                  <div className="mb-8">
                    <img
                      src="https://hebbkx1anhila5yf.public.blob.vercel-storage.com/package-i1idXlR6nZ7Xmlt5KmJJQYiUmmIQvW.png"
                      alt="Diagrama de Paquetes - Arquitectura por Capas"
                      className="w-full max-w-6xl mx-auto rounded-lg border"
                    />
                  </div>

                  <div className="space-y-8">
                    <div>
                      <h4 className="font-bold mb-4">Capas del Sistema</h4>
                      <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
                        <div className="bg-primary/10 p-4 rounded-lg border-l-4 border-primary">
                          <h5 className="font-bold text-primary mb-2">Controller</h5>
                          <p className="text-sm text-muted-foreground">Manejo de peticiones HTTP y respuestas</p>
                        </div>
                        <div className="bg-secondary/10 p-4 rounded-lg border-l-4 border-secondary">
                          <h5 className="font-bold text-secondary mb-2">Service</h5>
                          <p className="text-sm text-muted-foreground">Lógica de negocio y validaciones</p>
                        </div>
                        <div className="bg-accent/10 p-4 rounded-lg border-l-4 border-accent">
                          <h5 className="font-bold text-accent mb-2">Repository</h5>
                          <p className="text-sm text-muted-foreground">Acceso y persistencia de datos</p>
                        </div>
                        <div className="bg-muted p-4 rounded-lg border-l-4 border-border">
                          <h5 className="font-bold mb-2">Model</h5>
                          <p className="text-sm text-muted-foreground">Entidades y estructura de datos</p>
                        </div>
                      </div>
                    </div>

                    <div>
                      <h4 className="font-bold mb-4">Módulos del Sistema</h4>
                      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                        <div className="bg-background border rounded-lg p-6">
                          <h5 className="font-bold text-primary mb-3 flex items-center gap-2">
                            <Users className="h-5 w-5" />
                            Users Module
                          </h5>
                          <div className="space-y-2 text-sm">
                            <div>• EmployeeController</div>
                            <div>• UserController</div>
                            <div>• EmployeeService</div>
                            <div>• UserService</div>
                            <div>• Customer & Employee Models</div>
                          </div>
                        </div>

                        <div className="bg-background border rounded-lg p-6">
                          <h5 className="font-bold text-primary mb-3 flex items-center gap-2">
                            <Film className="h-5 w-5" />
                            Movies Module
                          </h5>
                          <div className="space-y-2 text-sm">
                            <div>• MovieController</div>
                            <div>• RoomController</div>
                            <div>• ScreeningController</div>
                            <div>• MovieService</div>
                            <div>• Movie & Room Models</div>
                          </div>
                        </div>

                        <div className="bg-background border rounded-lg p-6">
                          <h5 className="font-bold text-primary mb-3 flex items-center gap-2">
                            <ShoppingCart className="h-5 w-5" />
                            Sales Module
                          </h5>
                          <div className="space-y-2 text-sm">
                            <div>• TicketController</div>
                            <div>• ReservationController</div>
                            <div>• FoodController</div>
                            <div>• TicketService</div>
                            <div>• Ticket & Food Models</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </CardContent>
              </Card>
            </TabsContent>
          </Tabs>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-muted/50 py-12 px-4">
        <div className="container mx-auto">
          <div className="grid md:grid-cols-4 gap-8">
            <div>
              <div className="flex items-center space-x-2 mb-4">
                <Film className="h-6 w-6 text-primary" />
                <span className="text-lg font-bold">Cinema Paradiso</span>
              </div>
              <p className="text-sm text-muted-foreground">
                Sistema integral de gestión cinematográfica con modelo CRUD completo y arquitectura por capas.
              </p>
            </div>

            <div>
              <h4 className="font-medium mb-4">Funcionalidades</h4>
              <div className="space-y-2 text-sm">
                <div className="text-muted-foreground">Gestión de Películas</div>
                <div className="text-muted-foreground">Control de Salas</div>
                <div className="text-muted-foreground">Sistema de Reservas</div>
                <div className="text-muted-foreground">Venta de Tickets</div>
              </div>
            </div>

            <div>
              <h4 className="font-medium mb-4">Tecnologías</h4>
              <div className="space-y-2 text-sm">
                <div className="text-muted-foreground">HTML5 & CSS3</div>
                <div className="text-muted-foreground">JavaScript ES6+</div>
                <div className="text-muted-foreground">React & Next.js</div>
                <div className="text-muted-foreground">Base de Datos SQL</div>
              </div>
            </div>

            <div>
              <h4 className="font-medium mb-4">Arquitectura</h4>
              <div className="space-y-2 text-sm text-muted-foreground">
                <div>Patrón MVC</div>
                <div>Arquitectura por Capas</div>
                <div>Modelo CRUD</div>
                <div>Separación de Responsabilidades</div>
              </div>
            </div>
          </div>

          <div className="border-t mt-8 pt-8 text-center text-sm text-muted-foreground">
            © 2024 Cinema Paradiso. Sistema de gestión cinematográfica con documentación técnica completa.
          </div>
        </div>
      </footer>
    </div>
  )
}
