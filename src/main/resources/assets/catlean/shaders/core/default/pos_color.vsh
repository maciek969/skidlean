#version 330 core

in vec3 Position;
in vec4 Color;

layout (std140) uniform VertexUBO {
    mat4 ProjMat;
    mat4 ModelViewMat;
};

out vec4 vertexColor;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

    vertexColor = Color;
}