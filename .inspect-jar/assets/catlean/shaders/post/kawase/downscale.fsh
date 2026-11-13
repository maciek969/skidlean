#version 330 core

in vec2 uv;
out vec4 color;

uniform sampler2D Sampler0;

layout(std140) uniform UBO {
    vec2 u_HalfTexelSize;
    float u_Offset;
};

void main() {
    color = (
        texture(Sampler0, uv) * 4 +
        texture(Sampler0, uv - u_HalfTexelSize * u_Offset) +
        texture(Sampler0, uv + u_HalfTexelSize * u_Offset) +
        texture(Sampler0, uv + vec2(u_HalfTexelSize.x, -u_HalfTexelSize.y) * u_Offset) +
        texture(Sampler0, uv - vec2(u_HalfTexelSize.x, -u_HalfTexelSize.y) * u_Offset)
    ) / 8;
    color.a = 1;
}
