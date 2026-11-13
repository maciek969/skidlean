#version 330 core

in vec3 Position;
in vec2 UV0;
in vec4 Color;

layout (std140) uniform VertexUBO {
    mat4 ProjMat;
    mat4 ModelViewMat;
};

out vec2 FragCoord;
out vec2 TexCoord;
out vec4 uSize1;

const vec2[4] RECT_VERTICES_COORDS = vec2[] (
    vec2(0.0, 0.0),
    vec2(0.0, 1.0),
    vec2(1.0, 1.0),
    vec2(1.0, 0.0)
);

void main() {
    FragCoord = RECT_VERTICES_COORDS[gl_VertexID % 4];
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    TexCoord = gl_Position.xy * 0.5 + 0.5;
    uSize1 = vec4(UV0.x * 10000.0, UV0.y * 10000.0, Color.z, Color.w);
}